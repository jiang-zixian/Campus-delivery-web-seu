package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.mystoreComment;

/**
 * 我的评论Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-15
 */
public interface mystoreCommentMapper 
{
    /**
     * 查询我的评论
     * 
     * @param commentId 我的评论主键
     * @return 我的评论
     */
    public mystoreComment selectmystoreCommentByCommentId(Long commentId);

    /**
     * 查询我的评论列表
     * 
     * @param mystoreComment 我的评论
     * @return 我的评论集合
     */
    public List<mystoreComment> selectmystoreCommentList(mystoreComment mystoreComment);

    /**
     * 新增我的评论
     * 
     * @param mystoreComment 我的评论
     * @return 结果
     */
    public int insertmystoreComment(mystoreComment mystoreComment);

    /**
     * 修改我的评论
     * 
     * @param mystoreComment 我的评论
     * @return 结果
     */
    public int updatemystoreComment(mystoreComment mystoreComment);

    /**
     * 删除我的评论
     * 
     * @param commentId 我的评论主键
     * @return 结果
     */
    public int deletemystoreCommentByCommentId(Long commentId);

    /**
     * 批量删除我的评论
     * 
     * @param commentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletemystoreCommentByCommentIds(Long[] commentIds);
}
