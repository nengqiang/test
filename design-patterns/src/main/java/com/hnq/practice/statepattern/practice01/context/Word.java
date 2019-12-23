package com.hnq.practice.statepattern.practice01.context;

import com.hnq.practice.statepattern.practice01.state.ILearningState;

/**
 * Word是单词类，维护了单词各元素及单词的学习状态，提供单词学习相关操作。对应于状态模式的参与者，Word是环境（上下文）Context。
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public class Word {

    private String spell;

    private String paraphrase;

    private String example;

    private ILearningState state;

    public Word() {
    }

    public Word(String spell, String paraphrase, String example, ILearningState state) {
        this.spell = spell;
        this.paraphrase = paraphrase;
        this.example = example;
        this.state = state;
    }

    public void showQuestion() {
        state.showQuestion(this);
    }

    public void know() {
        state = state.know();
    }

    public void unKnow() {
        state = state.unKnow();
        System.out.println("释义：" + paraphrase);
    }

    public boolean needStudy() {
        return state.needStudy();
    }

    public String getSpell() {
        return spell;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    public String getExample() {
        return example;
    }

    public ILearningState getState() {
        return state;
    }

}
