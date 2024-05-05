package com.ruoyi.application.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家申请对象 cstore
 * 
 * @author ruoyi
 * @date 2024-05-05
 */
public class Cstore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private Long cstoreId;

    /**  */
    @Excel(name = "")
    private Long uId;

    /**  */
    @Excel(name = "")
    private String tel;

    /**  */
    @Excel(name = "")
    private String cardId;

    /**  */
    @Excel(name = "")
    private String name;

    /**  */
    @Excel(name = "")
    private String license;

    /**  */
    @Excel(name = "")
    private Integer pass;

    /**  */
    @Excel(name = "")
    private Integer status;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLicense(String license) 
    {
        this.license = license;
    }

    public String getLicense() 
    {
        return license;
    }
    public void setPass(Integer pass) 
    {
        this.pass = pass;
    }

    public Integer getPass() 
    {
        return pass;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
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
            .append("name", getName())
            .append("license", getLicense())
            .append("pass", getPass())
            .append("status", getStatus())
            .toString();
    }
}
