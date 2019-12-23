package com.hnq.test;

import com.google.common.base.Stopwatch;
import com.hnq.test.function.WordsAnalyzer;
import org.ansj.lucene5.AnsjAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author henengqiang
 * @date 2019/09/25
 */
class AnalyzerTest {

    @Test
    void ikSplitTest() {
        Analyzer analyzer = new IKAnalyzer(true);

        TokenStream ts = null;
        String sentence = "这个利息高得有点恐怖啊，合同说年利率百分11借41000元现在一年12347元的利息，这个就是高利贷啊，这个逾期1天收了200多的罚息。";
        try {
            ts = analyzer.tokenStream("myfield", new StringReader(sentence));
            // 获取词元位置属性
            OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
            // 获取词元文本属性
            CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
            // 获取词元文本属性
            TypeAttribute type = ts.addAttribute(TypeAttribute.class);

            // 重置TokenStream（重置StringReader）
            ts.reset();
            // 迭代获取分词结果
            while (ts.incrementToken()) {
                System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | " + type.type());
            }
            // 关闭TokenStream
            ts.end();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ts != null) {
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void getLengthOrderTermListTest() {
        Analyzer analyzer = new IKAnalyzer(true);
        String sentence = "正品 双头眉笔防水 防汗眉粉彩妆韩国画眉神器 自动带眉刷 一字眉 等多件";
        System.out.println(WordsAnalyzer.getLengthOrderTermList(analyzer, sentence));
    }

    @Test
    void ansjAnalyzerTest() throws IOException {
        Analyzer analyzer = new AnsjAnalyzer("nlp");
        String sentence = "[1934]屈臣氏杭州城西银泰购物中心店天才【双11全球狂欢节】水星家纺 枕头 超柔软枕心 压不扁枕头 枕芯正品 特价 一对拍2只我决定地接服务解决王成天才我从小就不由自主地认为自己长大以后一定得成为一个象我父亲一样的画家, 可能是父母潜移默化的影响。其实我根本不知道作为画家意味着什么，我是否喜欢，最重要的是否适合我，我是否有这个才华。其实人到中年的我还是不确定我最喜欢什么，最想做的是什么？我相信很多人和我一样有同样的烦恼。毕竟不是每个人都能成为作文里的宇航员，科学家和大教授。知道自己适合做什么，喜欢做什么，能做好什么其实是个非常困难的问题。";
        TokenStream ts = analyzer.tokenStream("sentence", new StringReader(sentence));

        Stopwatch watch = Stopwatch.createStarted();
        // 获取词元位置属性
        OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
        // 获取词元文本属性
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        // 获取词元文本属性
        TypeAttribute type = ts.addAttribute(TypeAttribute.class);

        ts.reset();
        while (ts.incrementToken()) {
            System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | " + type.type());
        }
        ts.end();
        ts.close();
        watch.stop();
        System.out.println(watch.toString());
    }

}
