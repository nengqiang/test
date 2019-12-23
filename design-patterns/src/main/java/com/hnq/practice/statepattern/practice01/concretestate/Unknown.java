package com.hnq.practice.statepattern.practice01.concretestate;

import com.hnq.practice.statepattern.practice01.context.Word;
import com.hnq.practice.statepattern.practice01.state.ILearningState;

/**
 * Unknown是学习中状态类，实现了学习状态接口ILearningState。对应于状态模式的参与者，Unknown是具体状态ConcreteState。
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public class Unknown implements ILearningState {

    @Override
    public void showQuestion(Word word) {
        System.out.println("学习模式：");
        System.out.println(word.getSpell());
        System.out.println(word.getExample());
    }

    @Override
    public ILearningState know() {
        return new ToBeTested();
    }

    @Override
    public ILearningState unKnow() {
        return this;
    }

    @Override
    public boolean needStudy() {
        return true;
    }
}
