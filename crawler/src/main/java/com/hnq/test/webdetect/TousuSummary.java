package com.hnq.test.webdetect;

import java.util.Date;

/**
 * @author henengqiang
 * @date 2019/11/04
 */
public class TousuSummary {

    private Date date;

    private String merchant;

    private Integer jutousuNum;

    private Integer blackcatNum;

    private Integer total;

    @Override
    public String toString() {
        return "TousuSummary{" +
                "date=" + date +
                ", merchant='" + merchant + '\'' +
                ", jutousuNum=" + jutousuNum +
                ", blackcatNum=" + blackcatNum +
                ", total=" + total +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Integer getJutousuNum() {
        return jutousuNum;
    }

    public void setJutousuNum(Integer jutousuNum) {
        this.jutousuNum = jutousuNum;
    }

    public Integer getBlackcatNum() {
        return blackcatNum;
    }

    public void setBlackcatNum(Integer blackcatNum) {
        this.blackcatNum = blackcatNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
