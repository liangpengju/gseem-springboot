package com.gseem.lesson03.dto;

import lombok.Data;

/**
 * @author liangpengju-ds
 */
@Data
public class PageParam {
    /**起始行*/
    private int start;
    /**每一页显示记录数目*/
    private Integer pageSize = 3;
    /**当前页*/
    private Integer currentPage=0;

    public PageParam() {
    }

    public PageParam(int start, Integer pageSize) {
        this.start = start;
        this.pageSize = pageSize;
    }
}
