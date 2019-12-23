package com.hnq.study.service.clean;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/10
 */
public interface DataCleaner<T> {

    /**
     * 保存
     * @param taskId
     */
    void storage(String taskId);

    /**
     * 洗数
     * @param taskId
     * @return
     */
    List<T> dataClean(String taskId);

}
