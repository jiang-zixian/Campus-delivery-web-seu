package com.ruoyi.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.record.mapper.RecordMapper;
import com.ruoyi.record.domain.Record;
import com.ruoyi.record.service.IRecordService;

/**
 * 我的订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
@Service
public class RecordServiceImpl implements IRecordService 
{
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 查询我的订单
     * 
     * @param recordId 我的订单主键
     * @return 我的订单
     */
    @Override
    public Record selectRecordByRecordId(Long recordId)
    {
        return recordMapper.selectRecordByRecordId(recordId);
    }

    /**
     * 查询我的订单列表
     * 
     * @param record 我的订单
     * @return 我的订单
     */
    @Override
    public List<Record> selectRecordList(Record record)
    {
        return recordMapper.selectRecordList(record);
    }

    /**
     * 新增我的订单
     * 
     * @param record 我的订单
     * @return 结果
     */
    @Override
    public int insertRecord(Record record)
    {
        return recordMapper.insertRecord(record);
    }

    /**
     * 修改我的订单
     * 
     * @param record 我的订单
     * @return 结果
     */
    @Override
    public int updateRecord(Record record)
    {
        return recordMapper.updateRecord(record);
    }

    /**
     * 批量删除我的订单
     * 
     * @param recordIds 需要删除的我的订单主键
     * @return 结果
     */
    @Override
    public int deleteRecordByRecordIds(Long[] recordIds)
    {
        return recordMapper.deleteRecordByRecordIds(recordIds);
    }

    /**
     * 删除我的订单信息
     * 
     * @param recordId 我的订单主键
     * @return 结果
     */
    @Override
    public int deleteRecordByRecordId(Long recordId)
    {
        return recordMapper.deleteRecordByRecordId(recordId);
    }
}
