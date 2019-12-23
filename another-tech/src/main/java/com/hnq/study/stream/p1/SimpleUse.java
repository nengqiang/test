package com.hnq.study.stream.p1;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author henengqiang
 * @date 2019/11/06
 * @see <a href="https://www.ibm.com/developerworks/cn/java/j-experience-stream/index.html">Stream<a/>
 */
class SimpleUse {

    private List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
    
    /**
     * create stream
     */
    @Test
    void createStream() {
        List<String> createStream = Lists.newArrayList();
        // 顺序流
        Stream<String> stream = createStream.stream();
        // 并行流
        Stream<String> parallelStream = createStream.parallelStream();
        // of()方法创建
        Stream<String> stringStream = Stream.of(createStream.toArray(new String[createStream.size()]));
    }

    /**
     * reduce 1
     */
    @Test
    void reduceFirstSign() {
        Optional<Integer> count = list.stream().reduce(Integer::sum);
        // 21
        count.ifPresent(System.out::println);
    }

    /**
     * reduce 2
     */
    @Test
    void reduceSecondSign() {
        Integer count = list.stream().reduce(2, (a, b) -> a * b);
        // 1440 所有元素乘积的2倍
        System.out.println(count);
    }

    /**
     * reduce 3
     */
    @Test
    void reduceThirdSign() {
        List<Integer> list = Lists.newArrayList(Integer.MAX_VALUE, Integer.MAX_VALUE);
        long count = list.stream().reduce(0L, Long::sum, (a, b) -> 0L);
        System.out.println(count);
    }

    /**
     * filter
     */
    @Test
    void filter() {
        List<Student> students = initData();
        List<Student> filterStudents = students.stream().filter(a -> a.getScore() < 60).collect(Collectors.toList());
        filterStudents.forEach(System.out::println);
    }

    /**
     * filter -> sum
     */
    @Test
    void calGrades() {
        List<Student> students = initData();
        double sum = students.stream()
                .filter(a -> a.getScore() > 60)
                .map(Student::getScore)
                .reduce(0D, Double::sum, (a, b) -> 0D);
        System.out.println(sum);
    }

    /**
     * 映射 -> 将一个集合转换成另外一个对象的集合
     * map() 和 flatMap() 方法
     */
    @Test
    void useMap() {
        List<Student> students = initData();
        double scoreCount = students.stream()
                .map(Student::getScore)
                .reduce(0.0, Double::sum);
        System.out.println(scoreCount);
    }

    /**
     * map()
     */
    @Test
    void useMapToDouble() {
        List<Student> students = initData();
        double scoreCount = students.stream()
                .mapToDouble(Student::getScore)
                .sum();
        System.out.println(scoreCount);
    }

    /**
     * flatMap()
     * flatMap()操作能把原始流总的元素进行一对多的转换，并且将新生成的元素全都合并到它返回的流程里面。
     */
    @Test
    void useFlatMap() {
        List<Student> students = initData();
        List<String> courses = students.stream()
                .flatMap(s -> s.getCourse().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(courses);
    }

    /**
     * Collector
     */
    @Test
    void listToMap() {
        List<Student> students = initData();
        Map<String, Double> collect = students.stream().collect(Collectors.toMap(Student::getName, Student::getScore));
        System.out.println(collect);
    }

    /**
     * parallel
     */
    @Test
    void parallel() {
        List<Integer> list = Lists.newArrayList(2, 2);
        Integer res = list.stream().parallel().reduce(2, Integer::sum, Integer::sum);
        System.out.println(res);
    }

    private List<Student> initData() {
        Student s1 = new Student("张三", 60, Lists.newArrayList("数学"));
        Student s2 = new Student("李四", 80, Lists.newArrayList("语文", "数学"));
        Student s3 = new Student("王五", 50, Lists.newArrayList("语文", "英语"));
        Student s4 = new Student("赵六", 70, Lists.newArrayList("数学", "化学"));
        Student s5 = new Student("孙七", 90, Lists.newArrayList("语文"));
        Student s6 = new Student("周八", 30, Lists.newArrayList("地理"));
        return Lists.newArrayList(s1, s2, s3, s4, s5, s6);
    }

}
