package com.hnq.study.question;

/**
 * 题目描述:
 * 现有程序同时启动了4个线程去调用TestDo.doSome(key,value)方法，由于TestDo.doSome(key,value)
 * 方法内的代码是先暂停1秒，然后再输出以秒为单位的当前时间值，所以，会打印出4个相同的时间值，
 * 如下所示:
 * 4:4:1258444892
 * 1:1:1258444892
 * 3:3:1258444892
 * 1:2:1258444892
 * 请修改代码，如果有几个线程调用TestDo.doSome(key,value)方法时，传递进去的key相等(equals比较为
 * true)，则这几个线程应互斥排队输出结果，即当有两个线程的key都是“1”时，它们中的一个要比另外
 * 其它线程晚1秒输出结果。如下所示:
 * 4:4:1258444892
 * 1:1:1258444892
 * 3:3:1258444892
 * 1:2:1258444896
 * 总之，当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间(要用互斥)，如果
 * key不同，则并行执行(相互之间不互斥)。
 *
 * see <a href="https://blog.csdn.net/acmman/article/details/53142264">Answer<a/>
 *
 * @author henengqiang
 * @date 2019/06/03
 */

/**
 * 不能改动此ThreadQ类
 */
class Interview3 extends Thread {

    private TestDo2 testDo2;
    private String key;
    private String value;

    private Interview3(String key, String key2, String value) {
        this.testDo2 = TestDo2.getInstance();
        /*
         * 常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的
         * 对象，以实现内容没有改变，仍然相等(都还为"1")，但对象却不再是同一个的效果
         * */
        this.key = key + key2;
        this.value = value;
    }

    public static void main(String[] args) {
        Interview3 a = new Interview3("1", "", "1");
        Interview3 b = new Interview3("1", "", "2");
        Interview3 c = new Interview3("3", "", "3");
        Interview3 d = new Interview3("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();
    }

    @Override
    public void run() {
        testDo2.doSome(key, value);
    }
}

class TestDo2 {
    private TestDo2() {
    }

    private static TestDo2 instance = new TestDo2();

    static TestDo2 getInstance() {
        return instance;
    }

    void doSome(String key, String value) {
        //以下大括号内的是需要局部同步的代码，不能改动！
        {
            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":"
                        + (System.currentTimeMillis() / 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}