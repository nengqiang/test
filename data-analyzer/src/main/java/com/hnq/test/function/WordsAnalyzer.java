package com.hnq.test.function;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/09/25
 */
@Slf4j
public class WordsAnalyzer {

    public static List<String> getLengthOrderTermList(Analyzer analyzer, String words) {
        List<String> result = Lists.newArrayList();
        // Lucene的TokenStream对象
        TokenStream ts = null;
        try {
            ts = analyzer.tokenStream("myfield", words);
            // 获取词元位置属性
            OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
            // 获取词元文本属性
            CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
            // 获取词元文本属性
            TypeAttribute type = ts.addAttribute(TypeAttribute.class);

            ts.reset();
            StringBuilder sb = new StringBuilder();
            while (ts.incrementToken()) {
                if (!result.contains(term.toString())) {
                    result.add(term.toString());
                    sb.append(term.toString()).append(" ");
                }
                log.info("{} - {} : {} | {}", offset.startOffset(), offset.endOffset(), term.toString(), type.type());
            }
            log.info(sb.toString());
            ts.end();
            result.sort((o1, o2) -> Integer.compare(o2.getBytes().length, o1.getBytes().length));
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (ts != null) {
                try {
                    ts.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return result;
    }

}
