package com.hnq.practice.statepattern.practice01.client;

import com.google.common.collect.Lists;
import com.hnq.practice.statepattern.practice01.concretestate.ToBeTested;
import com.hnq.practice.statepattern.practice01.context.Word;

import java.util.List;
import java.util.Scanner;

/**
 * WordLearning是单词学习类，提供了单词学习相关方法。对应于状态模式的参与者，WordLearning是客户Client
 *
 * @author henengqiang
 * @date 2019/07/30
 */
public class WordLearning {

    public void learn() {
        List<Word> words = loadWords();
        Scanner sc = new Scanner(System.in);
        System.out.println("学习开始（y表示认识该单词，n表示不认识该单词）");
        while (true) {
            for (Word word : words) {
                if (!word.needStudy()) {
                    continue;
                }
                word.showQuestion();
                String isKnownStr = sc.nextLine();
                if ("y".equals(isKnownStr)) {
                    word.know();
                } else {
                    word.unKnow();
                }
            }

            boolean hasLearningWord = false;
            for (Word word : words) {
                if (word.needStudy()) {
                    hasLearningWord = true;
                    break;
                }
            }
            if (!hasLearningWord) {
                break;
            }
        }
        System.out.println("学习结束");
    }

    private List<Word> loadWords() {
        Word word1 = new Word("hello", "您好", "Hello world!", new ToBeTested());
        Word word2 = new Word("world", "世界", "Hello world!", new ToBeTested());
        return Lists.newArrayList(word1, word2);
    }

}
