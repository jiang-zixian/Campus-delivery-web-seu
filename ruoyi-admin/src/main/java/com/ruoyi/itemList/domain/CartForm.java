package com.ruoyi.itemList.domain;

/**
 * @author Xu Guowei
 * @create 2024-05-14 16:04
 */
import java.util.Map;

public class CartForm {
    private Map<Long, Integer> items;

    // getters and setters
    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }
}