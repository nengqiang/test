package com.hnq.practice.statepattern.practice01;

import com.hnq.practice.statepattern.practice01.client.WordLearning;

/**
 * 1 State
 * State是状态接口，定义了与状态相关的各接口方法。
 * 2 ConcreteState
 * ConcreteState是具体状态，实现了接口State。
 * 3 Context
 * Context是环境（上下文）。Context对象中包含State类型对象以表示当前环境的状态。初始时由外部（或默认）指定Context所处状态。
 * 当Context对外提供的方法被调用时（如Request），Context将调用其包含的State对象的对应方法（如Handle），并根据调用结果更新State实例。
 *
 * 场景介绍
 * 某单词记忆应用的背单词功能将单词分为待测试、学习中和已掌握三种状态。应用以循环播放单词让用户选择是否认识该单词的形式帮助用户记忆、测试单词。
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public class Application {

    public static void main(String[] args) {
        WordLearning learning = new WordLearning();
        learning.learn();
    }

}
