package com.gseem.lesson03.entity;

import java.util.Date;

/**
 * 基础类
 */
public class BaseEntity {

    private Date createtime;

    private Date updatetime;

    private Integer status;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
