package com.zss.esms.util;

import com.zss.esms.page.Pagenation;
import org.springframework.data.domain.Page;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 16:28
 * @desc 分页工具
 */
public class PageUtil {

    /**
     * 将Spring Data的Page转为自己的Paenation
     *
     * @param page 分页信息
     * @return Pagenation
     */
    public static Pagenation converter(Page<?> page) {
        return Pagenation.builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .pageNumber(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .hasNext(page.hasNext())
                .build();
    }
}
