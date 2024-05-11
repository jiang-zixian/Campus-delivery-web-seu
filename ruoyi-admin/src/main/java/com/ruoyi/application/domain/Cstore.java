package com.ruoyi.application.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家提交申请对象 cstore
 * 
 * @author xuguowei
 * @date 2024-05-11
 */
public class Cstore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    @Excel(name = "申请ID")
    private Long cstoreId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long uId;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 身份证 */
    @Excel(name = "身份证")
    private String cardId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String sname;

    /** 营业执照 */
    @Excel(name = "营业执照")
    private String license;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    public void setCstoreId(Long cstoreId) 
    {
        this.cstoreId = cstoreId;
    }

    public Long getCstoreId() 
    {
        return cstoreId;
    }
    public void setuId(Long uId) 
    {
        this.uId = uId;
    }

    public Long getuId() 
    {
        return uId;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setCardId(String cardId) 
    {
        this.cardId = cardId;
    }

    public String getCardId() 
    {
        return cardId;
    }
    public void setSname(String sname) 
    {
        this.sname = sname;
    }

    public String getSname() 
    {
        return sname;
    }
    public void setLicense(String license) 
    {
        this.license = license;
    }

    public String getLicense() 
    {
        return license;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cstoreId", getCstoreId())
            .append("uId", getuId())
            .append("tel", getTel())
            .append("cardId", getCardId())
            .append("sname", getSname())
            .append("license", getLicense())
            .append("status", getStatus())
            .toString();
    }
}
