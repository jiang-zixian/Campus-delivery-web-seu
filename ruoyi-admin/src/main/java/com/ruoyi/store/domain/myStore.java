package com.ruoyi.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的商店对象 store
 * 
 * @author xpl
 * @date 2024-05-13
 */
public class myStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商店号 */
    @Excel(name = "商店号")
    private Long sId;

    /** 店家号 */
    @Excel(name = "店家号")
    private Long uId;

    /** 商店名称 */
    @Excel(name = "商店名称")
    private String sname;

    /** 图标 */
    @Excel(name = "图标")
    private String logo;

    /** 商店描述 */
    @Excel(name = "商店描述")
    private String description;

    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setuId(Long uId) 
    {
        this.uId = uId;
    }

    public Long getuId() 
    {
        return uId;
    }
    public void setSname(String sname) 
    {
        this.sname = sname;
    }

    public String getSname() 
    {
        return sname;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("uId", getuId())
            .append("sname", getSname())
            .append("logo", getLogo())
            .append("description", getDescription())
            .toString();
    }
}
