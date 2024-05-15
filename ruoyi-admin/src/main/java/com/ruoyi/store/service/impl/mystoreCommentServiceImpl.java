package com.ruoyi.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.mystoreCommentMapper;
import com.ruoyi.store.domain.mystoreComment;
import com.ruoyi.store.service.ImystoreCommentService;

/**
 * 我的评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
@Service
public class mystoreCommentServiceImpl implements ImystoreCommentService 
{
    @Autowired
    private mystoreCommentMapper mystoreCommentMapper;

    /**
     * 查询我的评论
     * 
     * @param commentId 我的评论主键
     * @return 我的评论
     */
    @Override
    public mystoreComment selectmystoreCommentByCommentId(Long commentId)
    {
        return mystoreCommentMapper.selectmystoreCommentByCommentId(commentId);
    }

    /**
     * 查询我的评论列表
     * 
     * @param mystoreComment 我的评论
     * @return 我的评论
     */
    @Override
    public List<mystoreComment> selectmystoreCommentList(mystoreComment mystoreComment)
    {
        return mystoreCommentMapper.selectmystoreCommentList(mystoreComment);
    }

    /**
     * 新增我的评论
     * 
     * @param mystoreComment 我的评论
     * @return 结果
     */
    @Override
    public int insertmystoreComment(mystoreComment mystoreComment)
    {
        return mystoreCommentMapper.insertmystoreComment(mystoreComment);
    }

    /**
     * 修改我的评论
     * 
     * @param mystoreComment 我的评论
     * @return 结果
     */
    @Override
    public int updatemystoreComment(mystoreComment mystoreComment)
    {
        return mystoreCommentMapper.updatemystoreComment(mystoreComment);
    }

    /**
     * 批量删除我的评论
     * 
     * @param commentIds 需要删除的我的评论主键
     * @return 结果
     */
    @Override
    public int deletemystoreCommentByCommentIds(Long[] commentIds)
    {
        return mystoreCommentMapper.deletemystoreCommentByCommentIds(commentIds);
    }

    /**
     * 删除我的评论信息
     * 
     * @param commentId 我的评论主键
     * @return 结果
     */
    @Override
    public int deletemystoreCommentByCommentId(Long commentId)
    {
        return mystoreCommentMapper.deletemystoreCommentByCommentId(commentId);
    }
}
