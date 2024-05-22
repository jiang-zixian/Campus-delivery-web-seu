package com.ruoyi.record.service.impl;

import java.util.List;

import com.ruoyi.store.domain.mystoreComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.record.mapper.myriderecordMapper;
import com.ruoyi.record.domain.myriderecord;
import com.ruoyi.record.service.ImyriderecordService;

/**
 * 我的跑腿订单Service业务层处理
 * 
 * @author xpl
 * @date 2024-05-16
 */
@Service
public class myriderecordServiceImpl implements ImyriderecordService 
{
    @Autowired
    private myriderecordMapper myriderecordMapper;

    /**
     * 查询我的跑腿订单
     * 
     * @param recordId 我的跑腿订单主键
     * @return 我的跑腿订单
     */
    @Override
    public myriderecord selectmyriderecordByRecordId(Long recordId)
    {
        return myriderecordMapper.selectmyriderecordByRecordId(recordId);
    }

    /**
     * 查询我的跑腿订单列表
     * 
     * @param myriderecord 我的跑腿订单
     * @return 我的跑腿订单
     */
    @Override
    public List<myriderecord> selectmyriderecordList(myriderecord myriderecord)
    {
        return myriderecordMapper.selectmyriderecordList(myriderecord);
    }

    /**
     * 新增我的跑腿订单
     * 
     * @param myriderecord 我的跑腿订单
     * @return 结果
     */
    @Override
    public int insertmyriderecord(myriderecord myriderecord)
    {
        return myriderecordMapper.insertmyriderecord(myriderecord);
    }

    /**
     * 修改我的跑腿订单
     * 
     * @param myriderecord 我的跑腿订单
     * @return 结果
     */
    @Override
    public int updatemyriderecord(myriderecord myriderecord)
    {
        return myriderecordMapper.updatemyriderecord(myriderecord);
    }

    /**
     * 批量删除我的跑腿订单
     * 
     * @param recordIds 需要删除的我的跑腿订单主键
     * @return 结果
     */
    @Override
    public int deletemyriderecordByRecordIds(Long[] recordIds)
    {
        return myriderecordMapper.deletemyriderecordByRecordIds(recordIds);
    }

    /**
     * 删除我的跑腿订单信息
     * 
     * @param recordId 我的跑腿订单主键
     * @return 结果
     */
    @Override
    public int deletemyriderecordByRecordId(Long recordId)
    {
        return myriderecordMapper.deletemyriderecordByRecordId(recordId);
    }

    @Override
    public int insertmyriderecordcomment(mystoreComment mystoreComment)
    {
        return myriderecordMapper.insertmyriderecordcomment(mystoreComment);
    }
}
