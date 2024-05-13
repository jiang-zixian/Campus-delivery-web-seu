package com.ruoyi.application.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 骑手申请对象 crider
 * 
 * @author xuguowei
 * @date 2024-05-04
 */
public class Crider extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请编号 */
    @Excel(name = "申请编号")
    private Long criderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uId;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String cardId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 是否通过 */
    @Excel(name = "是否通过")
    private Integer pass;

    /** 是否处理 */
    @Excel(name = "是否处理")
    private Integer status;

    public void setCriderId(Long criderId) 
    {
        this.criderId = criderId;
    }

    public Long getCriderId() 
    {
        return criderId;
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
            .append("criderId", getCriderId())
            .append("uId", getuId())
            .append("tel", getTel())
            .append("cardId", getCardId())
            .append("name", getName())
            .append("pass", getPass())
            .append("status", getStatus())
            .toString();
    }
}
