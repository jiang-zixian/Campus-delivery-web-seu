package com.ruoyi.itemList.domain;

/**
 * @author Xu Guowei
 * @create 2024-05-14 16:04
 */
public class CartForm {
    private static final long serialVersionUID = 1L;
    private Long iId;
    private Integer num;

    // getters and setters
    public Long getIId() {
        return iId;
    }

    public void setIId(Long iId) {
        this.iId = iId;
    }

    public Integer getNum() {
        return num;
    }
}