package org.example.rbac.security.payload;

import lombok.Data;

@Data
public class PageCondition {
    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
