package org.loxf.metric.common.dto;

/**
 * Created by hutingting on 2017/7/22.
 */
public class RateLimitDto {
    private String bussinessType;//业务类型

    private Integer rate;//每秒流量个数

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
