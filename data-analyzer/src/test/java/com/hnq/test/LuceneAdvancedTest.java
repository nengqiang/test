package com.hnq.test;

import com.hnq.test.consts.Constants;
import com.hnq.toolkit.util.StrUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author henengqiang
 * @date 2019/09/24
 */
class LuceneAdvancedTest {

    @Test
    void highlightTest() throws IOException, ParseException, InvalidTokenOffsetsException {
        Directory directory = FSDirectory.open(new File(Constants.IDX_DIR).toPath());
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");

        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer scorer = new QueryScorer(query);

        Highlighter highlighter = new Highlighter(formatter, scorer);

        TopDocs topDocs = searcher.search(query, 10);
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document doc = reader.document(docId);
            String result = StrUtils.format("id={} title={} \nscore={}",
                    doc.get("id"),
                    highlighter.getBestFragment(new IKAnalyzer(), "title", doc.get("title")),
                    scoreDoc.score);
            System.out.println(result);
            System.out.println("----------------------------");
        }

    }

    @Test
    void sortQueryTest() throws IOException, ParseException {
        Directory dir = FSDirectory.open(Paths.get(Constants.IDX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");

        // reverse-false升序 true降序
        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, true));

        TopDocs topDocs = searcher.search(query, 10, sort);
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc doc : scoreDocs) {
            int docId = doc.doc;
            Document document = reader.document(docId);
            System.out.println("id=" + document.get("id"));
            System.out.println("title=" + document.get("title"));
        }
    }

    @Test
    void pageQueryTest() throws IOException, ParseException {
        // 实际上Lucene本身不支持分页。因此我们需要自己进行逻辑分页。我们要准备分页参数
        int pageSize = 1;
        int pageNum = 3;
        int start = (pageNum - 1) * pageSize;
        int end = start + pageSize;

        Directory dir = FSDirectory.open(Paths.get(Constants.IDX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");

        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, false));
        TopDocs topDocs = searcher.search(query, end, sort);
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document doc = reader.document(docId);
            System.out.println("id=" + doc.get("id"));
            System.out.println("title=" + doc.get("title"));
        }
    }

}
