package com.hnq.practice.statepattern.practice01.concretestate;

import com.hnq.practice.statepattern.practice01.context.Word;
import com.hnq.practice.statepattern.practice01.state.ILearningState;

/**
 * ToBeTested是待测验状态类，实现了学习状态接口ILearningState。对应于状态模式的参与者，ToBeTested是具体状态ConcreteState。
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public class ToBeTested implements ILearningState {

    @Override
    public void showQuestion(Word word) {
        System.out.println("测试模式：");
        System.out.println(word.getSpell());
    }

    @Override
    public ILearningState know() {
        return new Known();
    }

    @Override
    public ILearningState unKnow() {
        return new Unknown();
    }

    @Override
    public boolean needStudy() {
        return true;
    }

}
