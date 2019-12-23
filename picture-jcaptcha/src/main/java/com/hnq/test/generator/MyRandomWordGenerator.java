package com.hnq.test.generator;

import com.hnq.test.constants.WordsOfCode;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author henengqiang
 * @date 2018/12/4
 */
public class MyRandomWordGenerator extends RandomWordGenerator {

    private char[] possiblesChars;

    private Random myRandom = new SecureRandom();

    public MyRandomWordGenerator(String acceptedChars) {
        super(acceptedChars);
        this.possiblesChars = acceptedChars.toCharArray();
    }

    @Override
    public String getWord(Integer length) {
        StringBuilder word = new StringBuilder(length);
        for(int i = 0; i < length; ++i) {
            word.append(this.possiblesChars[this.myRandom.nextInt(this.possiblesChars.length)]);
        }
        WordsOfCode.code = word.toString();
        return word.toString();
    }

}
