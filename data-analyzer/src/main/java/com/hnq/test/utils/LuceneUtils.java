package com.hnq.test.utils;

import com.hnq.test.consts.Constants;
import com.hnq.toolkit.util.StrUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

/**
 * @author henengqiang
 * @date 2019/09/24
 */
public class LuceneUtils {

    public static void search(Query query) throws Exception {
        // 索引目录对象
        Directory directory = FSDirectory.open(new File(Constants.IDX_DIR).toPath());
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        System.out.println("--------------------------------");
        // 获取得分文档对象（ScoreDoc）数组.ScoreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docId = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docId);
            String result = StrUtils.format("id={} title={} \nscore={}", doc.get("id"), doc.get("title"), scoreDoc.score);
            System.out.println(result);
            System.out.println("--------------------------------");
        }
    }


}
