package com.ruoyi.itemList.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品列表对象 item
 * 
 * @author SYL
 * @date 2024-05-13
 */
public class Item extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long iId;

    /** 店家编号 */
    @Excel(name = "店家编号")
    private Long sId;

    /** 商品图像 */
    @Excel(name = "商品图像")
    private String photo;

    /** 商品名 */
    @Excel(name = "商品名")
    private String itemName;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long amount;

    public void setiId(Long iId) 
    {
        this.iId = iId;
    }

    public Long getiId() 
    {
        return iId;
    }
    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iId", getiId())
            .append("sId", getsId())
            .append("photo", getPhoto())
            .append("itemName", getItemName())
            .append("price", getPrice())
            .append("amount", getAmount())
            .toString();
    }
}
