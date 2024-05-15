package com.ruoyi.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的评论对象 comment
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public class mystoreComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论号 */
    @Excel(name = "评论号")
    private Long commentId;

    /** 客户号 */
    @Excel(name = "客户号")
    private Long uId;

    /** 商店号 */
    @Excel(name = "商店号")
    private Long sId;

    /** 评论 */
    @Excel(name = "评论")
    private String comment;

    /** 追加评论 */
    @Excel(name = "追加评论")
    private String recomment;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long recordId;

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
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
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setRecomment(String recomment) 
    {
        this.recomment = recomment;
    }

    public String getRecomment() 
    {
        return recomment;
    }
    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("uId", getuId())
            .append("sId", getsId())
            .append("comment", getComment())
            .append("recomment", getRecomment())
            .append("recordId", getRecordId())
            .toString();
    }
}
