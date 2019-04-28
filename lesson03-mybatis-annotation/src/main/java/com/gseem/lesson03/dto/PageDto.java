package com.gseem.lesson03.dto;

import lombok.Data;

/**
 * 数据分页返回对象
 */
@Data
public class PageDto {
    private int count;
    private Object data;

    public PageDto() {
    }

    public PageDto(int count, Object data) {
        this.count = count;
        this.data = data;
    }
}
