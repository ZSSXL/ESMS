package com.zss.esms.page;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 15:43
 * @desc 分页信息封装
 */
@Builder
@SuppressWarnings("unused")
public class Pagenation implements Serializable {

    /**
     * 1、int totalPages         总页数
     * 2、long totalElements     总元素
     * 3、List<Object> content   当前页内容
     * 4、int pageNumber         页码
     * 5、int pageSize           页大小
     * 6、boolean isFirst        是否第一页
     * 7、boolen isLast          是否最后一页
     */
    private Integer totalPages;

    private Long totalElements;

    private List<?> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Boolean isFirst;

    private Boolean isLast;

    private Boolean hasNext;

    // 有参 & 无参构造方法

    public Pagenation() {
    }

    public Pagenation(Integer totalPages, Long totalElements, List<?> content, Integer pageNumber, Integer pageSize, Boolean isFirst, Boolean isLast, Boolean hasNext) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.hasNext = hasNext;
    }

    // Getter & Setter方法

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    // toString 方法


    @Override
    public String toString() {
        return "Pagenation{" +
                "totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", content=" + content +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", isFirst=" + isFirst +
                ", isLast=" + isLast +
                ", hasNext=" + hasNext +
                '}';
    }
}
