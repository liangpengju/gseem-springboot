package com.gseem.lesson04.dto.resp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果抽象类
 *
 * @author liangpengju-ds
 */
@Data
public class PagingRes<T> implements Serializable {
    protected static final long serialVersionUID = 1L;

    // 单页记录数
    private int pageSize;

    // 查询结果记录总数
    private long total;

    private List<T> data;

    public PagingRes(){

    }

    public PagingRes(int pageSize, long total, List<T> data) {
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }
}
