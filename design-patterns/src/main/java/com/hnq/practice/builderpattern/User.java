package com.hnq.practice.builderpattern;

/**
 * 构造者模式
 *
 * @author henengqiang
 * @date 2019/02/27
 */
public class User {

    /**
     * 必需
     */
    private String firstName;

    /**
     * 必需
     */
    private String lastName;

    private Integer age;

    private String phone;

    private String address;

    public User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    /**
     * 静态内部类--隐藏
     */
    static class Builder {

        /**
         * 必需
         */
        private String firstName;

        /**
         * 必需
         */
        private String lastName;

        private Integer age;

        private String phone;

        private String address;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    // -----------------------------------------------

    public static void main(String[] args) {
        User user = new User.Builder("Alice", "Kitty").setAge(25).setPhone("13774248988").build();
        System.out.println(user);
    }
}
