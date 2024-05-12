package com.ruoyi.itemcart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车对象 itemcart
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
public class Itemcart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 购物车号 */
    @Excel(name = "购物车号")
    private Long itemcartId;

    /** 客户号 */
    @Excel(name = "客户号")
    private Long uId;

    /** 商品号 */
    @Excel(name = "商品号")
    private Long iId;

    /** 商品数目 */
    @Excel(name = "商品数目")
    private Long number;

    public void setItemcartId(Long itemcartId) 
    {
        this.itemcartId = itemcartId;
    }

    public Long getItemcartId() 
    {
        return itemcartId;
    }
    public void setuId(Long uId) 
    {
        this.uId = uId;
    }

    public Long getuId() 
    {
        return uId;
    }
    public void setiId(Long iId) 
    {
        this.iId = iId;
    }

    public Long getiId() 
    {
        return iId;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemcartId", getItemcartId())
            .append("uId", getuId())
            .append("iId", getiId())
            .append("number", getNumber())
            .toString();
    }
}
