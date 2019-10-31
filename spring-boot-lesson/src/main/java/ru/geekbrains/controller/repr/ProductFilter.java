package ru.geekbrains.controller.repr;

import java.math.BigDecimal;

public class ProductFilter {

    private Long categoryId;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private Integer currentPage;

    private Integer pageSize;

    private Integer pageCount;

    public ProductFilter() {
    }

    public ProductFilter(Long categoryId, BigDecimal priceFrom, BigDecimal priceTo, Integer currentPage, Integer pageSize) {
        this.categoryId = categoryId;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public void applyItemCount(Integer itemCount) {
        this.pageCount = (itemCount + pageSize - 1) / pageSize;
        this.currentPage = currentPage >= pageCount ? pageCount - 1 : currentPage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPrevPage() {
        return currentPage == 0 ? 0 : currentPage - 1;
    }

    public Integer getNextPage() {
        return currentPage == pageCount - 1 ? currentPage : currentPage + 1;
    }
}
