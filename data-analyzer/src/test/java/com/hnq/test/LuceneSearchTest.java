package com.hnq.test;
import	java.nio.file.Path;

import com.google.common.collect.Maps;
import com.hnq.test.bean.GroupResult;
import com.hnq.test.consts.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.facet.*;
import org.apache.lucene.facet.taxonomy.FastTaxonomyFacetCounts;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParserBase;
import org.apache.lucene.search.*;
import org.apache.lucene.store.SimpleFSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/09/26
 */
public class LuceneSearchTest {

    /* 创建简单中文分析器 创建索引使用的分词器必须和查询时候使用的分词器一样，否则查询不到想要的结果 */
    private Analyzer analyzer = new IKAnalyzer();

    private Path indexPath = Paths.get(Constants.IDX_DIR);

    private Path taxoPath = Paths.get(Constants.TAXO_DIR);

    private final FacetsConfig config = new FacetsConfig();

    public String search(String keyword, int size) {
        StringBuilder result = new StringBuilder();
        try {
            IndexSearcher idxSearcher = new IndexSearcher(DirectoryReader.open(new SimpleFSDirectory(indexPath)));
            TaxonomyReader taxoReader = new DirectoryTaxonomyReader(new SimpleFSDirectory(taxoPath));

            String[] fields = {"keyword", "t_keyword"};

            QueryParser qp = new MultiFieldQueryParser(fields, analyzer);
            qp.setDefaultOperator(QueryParser.OR_OPERATOR);
            Query query = qp.parse(QueryParserBase.escape(keyword));

            System.out.println("keyword = " + keyword);
            System.out.println("query = " + query);

            // 搜索相似度最高的size条记录
            TopDocs topDocs = idxSearcher.search(query, size);
            System.out.println("命中：" + topDocs.totalHits);
            // 输出结果
            GroupResult bestGroupResult = getBestGroupResult(topDocs, idxSearcher, keyword);
            BooleanQuery.Builder bqBuilder = new BooleanQuery.Builder();
            if (bestGroupResult != null) {
                for (Document document : bestGroupResult.getList()) {
                    System.out.println("过滤后的内容：" + document);
                    bqBuilder.add(new TermQuery(new Term("id", document.get("id"))), BooleanClause.Occur.SHOULD);
                }

                FacetsCollector fc = new FacetsCollector();
                FacetsCollector.search(idxSearcher, bqBuilder.build(), 10, fc);
                Facets pathFacets = new FastTaxonomyFacetCounts("path", taxoReader, config, fc);
                FacetResult facetsResult = pathFacets.getTopChildren(10, "category path");
                StringBuilder path = new StringBuilder("category path");
                System.out.println(path + " top 10 " + resetFacetResult(facetsResult, result));

                while (facetsResult != null && facetsResult.childCount > 0) {
                    path.append("/").append(facetsResult.labelValues[0].label);
                    facetsResult = pathFacets.getTopChildren(10, "category path", facetsResult.labelValues[0].label);
                    System.out.println(path + " top 10 " + resetFacetResult(facetsResult, result));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private String resetFacetResult(FacetResult facetResult, StringBuilder result) {
        StringBuilder sb = new StringBuilder();
        if (facetResult != null && facetResult.childCount > 0) {
            sb.append("dim=")
                    .append(facetResult.dim)
                    .append(" path=")
                    .append(Arrays.toString(facetResult.path))
                    .append(" value=")
                    .append(facetResult.value)
                    .append(" childCount=")
                    .append(facetResult.childCount)
                    .append('\n');
            for (LabelAndValue label : facetResult.labelValues) {
                sb.append(label.label).append(" > ").append("???");
            }
            result.append("???");
        }
        return sb.toString();
    }

    private GroupResult getBestGroupResult(TopDocs topDocs, IndexSearcher idxSearcher, String score) {
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Map<String, GroupResult> resultMap = Maps.newHashMap();
        for (ScoreDoc scoreDoc : scoreDocs) {
            try {
                Document targetDoc = idxSearcher.doc(scoreDoc.doc);
                System.out.println("内容：" + targetDoc.toString() + ", " + scoreDoc.score);
                GroupResult result = resultMap.get(StringUtils.substringBefore(targetDoc.get("path"), "/"));
                if (result == null) {
                    result = new GroupResult();
                    result.setPath(StringUtils.substringBefore(targetDoc.get("path"), "/"));
                    resultMap.put(result.getPath(), result);
                }
                result.setScore(result.getScore() + getScore(score, targetDoc));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        GroupResult bestGroupResult = null;
        for (GroupResult value : resultMap.values()) {
            if (bestGroupResult == null || value.getScore() > bestGroupResult.getScore()) {
                bestGroupResult = value;
            }
        }
        return bestGroupResult;
    }

    private float getScore(String source, Document targetDoc) {
        String keyword = targetDoc.get("keyword");
        if (StringUtils.contains(source, keyword)) {
            if (keyword.equals(source)) {
                return Integer.MAX_VALUE;
            } else {
                return keyword.length() * source.length() / (source.length() - keyword.length());
            }
        } else {
            return 1F;
        }
    }

}
