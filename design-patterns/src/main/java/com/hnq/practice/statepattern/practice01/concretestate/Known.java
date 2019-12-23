package com.hnq.practice.statepattern.practice01.concretestate;

import com.hnq.practice.statepattern.practice01.context.Word;
import com.hnq.practice.statepattern.practice01.state.ILearningState;

/**
 * Known是已掌握状态类，实现了学习状态接口ILearningState。对应于状态模式的参与者，Known是具体状态ConcreteState。
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public class Known implements ILearningState {

    @Override
    public void showQuestion(Word word) {
        System.out.println("复习模式：");
        System.out.println(word.getSpell());
    }

    @Override
    public ILearningState know() {
        return this;
    }

    @Override
    public ILearningState unKnow() {
        return new Unknown();
    }

    @Override
    public boolean needStudy() {
        return false;
    }

}
