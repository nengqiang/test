package com.hnq.test;

import com.hnq.test.consts.Constants;
import com.hnq.test.utils.LuceneUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * @author henengqiang
 * @date 2019/09/23
 */
class LuceneTest {

    /**
     * 创建索引
     */
    @Test
    void indexCreateTest() throws IOException {
        // 1.创建文档对象
        Document document = new Document();
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new TextField("title", "facebook", Field.Store.YES));

        // 2.索引目录类
        Directory directory = FSDirectory.open(new File(Constants.IDX_DIR).toPath());
        // 3.创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        // 4.索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        // 5.创建索引的写出工具类，参数：索引的目录和配置信息
        IndexWriter writer = new IndexWriter(directory, conf);

        // 6.把文档交给IndexWriter
        writer.addDocument(document);
        // 7.提交
        writer.commit();
        // 8.关闭
        writer.close();

        System.out.println("All operations finished.");
    }

    /**
     * 批量创建索引
     */
    @Test
    void indexCreateTest2() throws Exception{
        // 创建文档的集合
        Collection<Document> docs = Lists.newArrayList();
        // 创建文档对象
        Document document1 = new Document();
        document1.add(new StringField("id", "1", Field.Store.YES));
        // Sort排序对索引字段有了新的要求，即使用DocValuesField的字段才能进行排序。
        document1.add(new NumericDocValuesField("id", 1L));
        document1.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));
        docs.add(document1);
        // 创建文档对象
        Document document2 = new Document();
        document2.add(new StringField("id", "2", Field.Store.YES));
        document2.add(new NumericDocValuesField("id", 2L));
        document2.add(new TextField("title", "谷歌地图之父加盟FaceBook", Field.Store.YES));
        docs.add(document2);
        // 创建文档对象
        Document document3 = new Document();
        document3.add(new StringField("id", "3", Field.Store.YES));
        document3.add(new NumericDocValuesField("id", 3L));
        document3.add(new TextField("title", "谷歌地图创始人拉斯离开谷歌加盟Facebook", Field.Store.YES));
        docs.add(document3);
        // 创建文档对象
        Document document4 = new Document();
        document4.add(new StringField("id", "4", Field.Store.YES));
        document4.add(new NumericDocValuesField("id", 4L));
        document4.add(new TextField("title", "谷歌地图之父跳槽Facebook与Wave项目取消有关", Field.Store.YES));
        docs.add(document4);
        // 创建文档对象
        Document document5 = new Document();
        document5.add(new StringField("id", "5", Field.Store.YES));
        document5.add(new NumericDocValuesField("id", 5L));
        document5.add(new TextField("title", "谷歌地图之父拉斯加盟社交网站Facebook", Field.Store.YES));
        docs.add(document5);

        // 索引目录类,指定索引在硬盘中的位置
        Directory directory = FSDirectory.open(new File(Constants.IDX_DIR).toPath());
        // 引入IK分词器
        Analyzer analyzer = new IKAnalyzer();
        // 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        // 把文档集合交给IndexWriter
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();

        System.out.println("All operations finished.");
    }

    @Test
    void deleteTest() throws IOException {
        Directory dic = FSDirectory.open(Paths.get(Constants.IDX_DIR));
        IndexWriterConfig conf = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(dic, conf);

        indexWriter.deleteAll();
        indexWriter.commit();
        indexWriter.close();
    }

    @Test
    void indexSearchTest() throws Exception {
        // 索引目录对象
        Directory directory = FSDirectory.open(new File(Constants.IDX_DIR).toPath());
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        // 创建查询解析器,两个参数：默认要查询的字段的名称，分词器
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        // 创建查询对象
        Query query = parser.parse("谷歌");

        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");

        // 获取得分文档对象（ScoreDoc）数组.ScoreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
        }
    }

    /**
     * 词条查询
     * term（词条）是搜索的最小单位，不可再分词。值必须是字符串！
     */
    @Test
    void termQueryTest() throws Exception {
        Query query = new TermQuery(new Term("title", "谷歌地图"));
        LuceneUtils.search(query);
    }

    /**
     * 通配符查询
     * ? 代表任意一个字符
     * * 代表任意多个字符
     */
    @Test
    void wildCardTest() throws Exception {
        Query query = new WildcardQuery(new Term("title", "*歌*"));
        LuceneUtils.search(query);
    }

    /**
     * 模糊查询
     * 允许用户输错，但要求错误的最大编辑距离不能超过2
     * 编辑距离：一个单词到另一个单词最少要修改的次数 例：facevook -> facebook 需要编辑1次，编辑距离就是1
     */
    @Test
    void fuzzQueryTest() throws Exception {
        Query query = new FuzzyQuery(new Term("title", "facevook"), 1);
        LuceneUtils.search(query);
    }

    /**
     * 数值范围查询
     * 用来对非String类型的id进行精确的查找
     */
    @Test
    void numericRangeQueryTest() throws Exception {
        // 字段名称 最小值 最大值 是否包含最小值 是否包含最大值
        Query query = NumericRangeQuery.newLongRange("id", 2L, 2L, true, true);
        LuceneUtils.search(query);
    }

    /**
     * 组合查询
     * 布尔查询
     *
     * 布尔查询本身没有查询条件，可以把其他查询通过逻辑运算进行组合
     * 交集： MUST + MUST
     * 并集： SHOULD + SHOULD
     * 非： MUST_NOT
     */
    @Test
    void booleanQueryTest() throws Exception {
        Query query1 = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
        Query query2 = NumericRangeQuery.newLongRange("id", 2L, 4L, true, true);

        BooleanQuery query = new BooleanQuery.Builder()
                .add(query1, BooleanClause.Occur.MUST_NOT)
                .add(query2, BooleanClause.Occur.SHOULD)
                .build();
        LuceneUtils.search(query);
    }


}
