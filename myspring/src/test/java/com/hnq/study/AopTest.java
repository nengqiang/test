package com.hnq.study;

import com.hnq.study.aop.Aop;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class AopTest {


    private static class TestAop implements Aop<String> {
        @Override
        public void pro() {
            System.out.println("aop前");
        }

        @Override
        public String after(String obj) {
            return obj + "---aop后";
        }
    }

}
