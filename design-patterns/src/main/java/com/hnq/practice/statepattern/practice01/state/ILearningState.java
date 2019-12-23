package com.hnq.practice.statepattern.practice01.state;

import com.hnq.practice.statepattern.practice01.context.Word;

/**
 * ILearningState是学习状态接口，定义了在不同状态下单词学习的各相关操作。对应于状态模式的参与者，ILearningState是状态接口State。
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public interface ILearningState {

    /**
     * 显示问题
     * @param word
     */
    void showQuestion(Word word);

    /**
     * 认识
     * @return
     */
    ILearningState know();

    /**
     * 不认识
     * @return
     */
    ILearningState unKnow();

    /**
     * 是否需要学习
     * @return
     */
    boolean needStudy();
}
