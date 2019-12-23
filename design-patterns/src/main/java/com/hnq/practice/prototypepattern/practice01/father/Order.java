package com.hnq.practice.prototypepattern.practice01.father;

import com.hnq.toolkit.util.DateUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Order 是单据类，声明了单据id、单据创建时间、单据创建人、单据类型等单据基本属性，并提供了单据信息的序列化方法。
 * Order 实现接口 Cloneable，重写了 clone 方法，返回拷贝自身信息生成的新的 Order 对象。
 * 对应于原型模式的参与者，Order 是我们的 Prototype。
 *
 * @author henengqiang
 * @date 2019/03/13
 */
public class Order implements Cloneable {

    /**
     * 单据id
     */
    private String id;

    /**
     * 单据创建时间
     */
    private Date createTime;

    /**
     * 单据创建人
     */
    private String creator;

    /**
     * 单据类型
     */
    private String type;

    public Order() {
    }

    public Order(String id, Date createTime, String creator, String type) {
        this.id = id;
        this.createTime = createTime;
        this.creator = creator;
        this.type = type;
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return     a clone of this instance.
     * @throws  CloneNotSupportedException  if the object's class does not
     *               support the {@code Cloneable} interface. Subclasses
     *               that override the {@code clone} method can also
     *               throw this exception to indicate that an instance cannot
     *               be cloned.
     * @see java.lang.Cloneable
     */
    @Override
    public Order clone() throws CloneNotSupportedException {
        return new Order(UUID.randomUUID().toString(), new Date(), this.creator, this.type);
    }

    @Override
    public String toString() {
        return String.format("单据类型：%s\n单号：%s\n创建人：%s\n创建时间：%s\n",
                type, id, creator, DateUtils.format(createTime, "yyyy-MM-dd HH:mm:ss"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
