package com.ruoyi.record.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的订单对象 record
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
public class Record extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long recordId;

    /** 客户号 */
    @Excel(name = "客户号")
    private Long uId;

    /** 商店号 */
    @Excel(name = "商店号")
    private Long sId;

    /** 总价 */
    @Excel(name = "总价")
    private Long allItemPrice;

    /** 骑手号 */
    @Excel(name = "骑手号")
    private Long riderId;

    /** 派送费 */
    @Excel(name = "派送费")
    private Long deliveryPrice;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long status;

    /** 取货地址 */
    @Excel(name = "取货地址")
    private String srcPosition;

    /** 送达地址 */
    @Excel(name = "送达地址")
    private String destPosition;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date srcTime;

    /** 送达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "送达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date destTime;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private Long type;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setuId(Long uId) 
    {
        this.uId = uId;
    }

    public Long getuId() 
    {
        return uId;
    }
    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setAllItemPrice(Long allItemPrice) 
    {
        this.allItemPrice = allItemPrice;
    }

    public Long getAllItemPrice() 
    {
        return allItemPrice;
    }
    public void setRiderId(Long riderId) 
    {
        this.riderId = riderId;
    }

    public Long getRiderId() 
    {
        return riderId;
    }
    public void setDeliveryPrice(Long deliveryPrice) 
    {
        this.deliveryPrice = deliveryPrice;
    }

    public Long getDeliveryPrice() 
    {
        return deliveryPrice;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setSrcPosition(String srcPosition) 
    {
        this.srcPosition = srcPosition;
    }

    public String getSrcPosition() 
    {
        return srcPosition;
    }
    public void setDestPosition(String destPosition) 
    {
        this.destPosition = destPosition;
    }

    public String getDestPosition() 
    {
        return destPosition;
    }
    public void setSrcTime(Date srcTime) 
    {
        this.srcTime = srcTime;
    }

    public Date getSrcTime() 
    {
        return srcTime;
    }
    public void setDestTime(Date destTime) 
    {
        this.destTime = destTime;
    }

    public Date getDestTime() 
    {
        return destTime;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("uId", getuId())
            .append("sId", getsId())
            .append("allItemPrice", getAllItemPrice())
            .append("riderId", getRiderId())
            .append("deliveryPrice", getDeliveryPrice())
            .append("status", getStatus())
            .append("srcPosition", getSrcPosition())
            .append("destPosition", getDestPosition())
            .append("srcTime", getSrcTime())
            .append("destTime", getDestTime())
            .append("type", getType())
            .toString();
    }
}
