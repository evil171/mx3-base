package com.example.mx3.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PageParam {

    private Integer current;

    private Integer size;

    public <T> Page<T> toPage() {
        if (this.current == null || this.current <= 0) {
            this.current = 1;
        }
        if (this.size == null || this.size > 500) {
            this.size = 15;
        }
        return new Page<>(this.current, this.size);
    }
}
