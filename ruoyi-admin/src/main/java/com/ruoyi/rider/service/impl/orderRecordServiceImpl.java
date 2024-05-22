package com.ruoyi.rider.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rider.mapper.orderRecordMapper;
import com.ruoyi.rider.domain.orderRecord;
import com.ruoyi.rider.service.IorderRecordService;

/**
 * 我要接单Service业务层处理
 * 
 * @author xpl
 * @date 2024-05-17
 */
@Service
public class orderRecordServiceImpl implements IorderRecordService 
{
    @Autowired
    private orderRecordMapper orderRecordMapper;

    /**
     * 查询我要接单
     * 
     * @param recordId 我要接单主键
     * @return 我要接单
     */
    @Override
    public orderRecord selectorderRecordByRecordId(Long recordId)
    {
        return orderRecordMapper.selectorderRecordByRecordId(recordId);
    }

    /**
     * 查询我要接单列表
     * 
     * @param orderRecord 我要接单
     * @return 我要接单
     */
    @Override
    public List<orderRecord> selectorderRecordList(orderRecord orderRecord)
    {
        return orderRecordMapper.selectorderRecordList(orderRecord);
    }

    /**
     * 新增我要接单
     * 
     * @param orderRecord 我要接单
     * @return 结果
     */
    @Override
    public int insertorderRecord(orderRecord orderRecord)
    {
        return orderRecordMapper.insertorderRecord(orderRecord);
    }

    /**
     * 修改我要接单
     * 
     * @param orderRecord 我要接单
     * @return 结果
     */
    @Override
    public int updateorderRecord(orderRecord orderRecord)
    {
        return orderRecordMapper.updateorderRecord(orderRecord);
    }

    /**
     * 批量删除我要接单
     * 
     * @param recordIds 需要删除的我要接单主键
     * @return 结果
     */
    @Override
    public int deleteorderRecordByRecordIds(Long[] recordIds)
    {
        return orderRecordMapper.deleteorderRecordByRecordIds(recordIds);
    }

    /**
     * 删除我要接单信息
     * 
     * @param recordId 我要接单主键
     * @return 结果
     */
    @Override
    public int deleteorderRecordByRecordId(Long recordId)
    {
        return orderRecordMapper.deleteorderRecordByRecordId(recordId);
    }
}
