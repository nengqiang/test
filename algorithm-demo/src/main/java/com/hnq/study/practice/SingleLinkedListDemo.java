package com.hnq.study.practice;

/**
 * 单向链表
 *
 * @author henengqiang
 * @date 2020/3/16
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "Alice", "alice");
        HeroNode node2 = new HeroNode(2, "Bob", "bob");
        HeroNode node3 = new HeroNode(3, "Candy", "candy");
        HeroNode node4 = new HeroNode(4, "Dandy", "dandy");
        HeroNode node5 = new HeroNode(5, "Franc", "franc");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node2);
        singleLinkedList.add(node1);
        singleLinkedList.add(node3);
        singleLinkedList.add(node4);
        singleLinkedList.add(node5);

        singleLinkedList.list();
    }



    static class HeroNode {
        int no;
        String name;
        String nickname;
        HeroNode next;

        HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname +
                    "}";
        }
    }

    static class SingleLinkedList {
        // 头结点，不存放任何数据
        private HeroNode head = new HeroNode(0, "", "");

        /**
         * 找到当前链表的最后一个节点
         * 并将该节点 next 指向新节点
         */
        public void add(HeroNode node) {
            // 头结点不能动，需要一个辅助指针
            HeroNode temp = head;
            // 遍历链表，找到最后
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            // 退出这个循环时，temp 就指向了链表的最后
            temp.next = node;
        }

        public void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

}
