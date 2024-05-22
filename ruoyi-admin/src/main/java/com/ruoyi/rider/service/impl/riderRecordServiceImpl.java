package com.ruoyi.rider.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rider.mapper.riderRecordMapper;
import com.ruoyi.rider.domain.riderRecord;
import com.ruoyi.rider.service.IriderRecordService;

/**
 * 历史接单记录Service业务层处理
 * 
 * @author xpl
 * @date 2024-05-22
 */
@Service
public class riderRecordServiceImpl implements IriderRecordService 
{
    @Autowired
    private riderRecordMapper riderRecordMapper;

    /**
     * 查询历史接单记录
     * 
     * @param recordId 历史接单记录主键
     * @return 历史接单记录
     */
    @Override
    public riderRecord selectriderRecordByRecordId(Long recordId)
    {
        return riderRecordMapper.selectriderRecordByRecordId(recordId);
    }

    /**
     * 查询历史接单记录列表
     * 
     * @param riderRecord 历史接单记录
     * @return 历史接单记录
     */
    @Override
    public List<riderRecord> selectriderRecordList(riderRecord riderRecord)
    {
        return riderRecordMapper.selectriderRecordList(riderRecord);
    }

    /**
     * 新增历史接单记录
     * 
     * @param riderRecord 历史接单记录
     * @return 结果
     */
    @Override
    public int insertriderRecord(riderRecord riderRecord)
    {
        return riderRecordMapper.insertriderRecord(riderRecord);
    }

    /**
     * 修改历史接单记录
     * 
     * @param riderRecord 历史接单记录
     * @return 结果
     */
    @Override
    public int updateriderRecord(riderRecord riderRecord)
    {
        return riderRecordMapper.updateriderRecord(riderRecord);
    }

    /**
     * 批量删除历史接单记录
     * 
     * @param recordIds 需要删除的历史接单记录主键
     * @return 结果
     */
    @Override
    public int deleteriderRecordByRecordIds(Long[] recordIds)
    {
        return riderRecordMapper.deleteriderRecordByRecordIds(recordIds);
    }

    /**
     * 删除历史接单记录信息
     * 
     * @param recordId 历史接单记录主键
     * @return 结果
     */
    @Override
    public int deleteriderRecordByRecordId(Long recordId)
    {
        return riderRecordMapper.deleteriderRecordByRecordId(recordId);
    }
}
