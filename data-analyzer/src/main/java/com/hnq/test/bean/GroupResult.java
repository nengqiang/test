package com.hnq.test.bean;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.lucene.document.Document;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/09/26
 */
@Data
public class GroupResult {

    private String path;

    private float score;

    List<Document> list = Lists.newArrayList();

}
