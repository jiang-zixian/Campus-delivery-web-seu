package com.ruoyi.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.record.mapper.zitiRecordMapper;
import com.ruoyi.record.domain.zitiRecord;
import com.ruoyi.record.service.IzitiRecordService;

/**
 * 自提订单Service业务层处理
 * 
 * @author wj
 * @date 2024-05-21
 */
@Service
public class zitiRecordServiceImpl implements IzitiRecordService 
{
    @Autowired
    private zitiRecordMapper zitiRecordMapper;

    /**
     * 查询自提订单
     * 
     * @param recordId 自提订单主键
     * @return 自提订单
     */
    @Override
    public zitiRecord selectzitiRecordByRecordId(Long recordId)
    {
        return zitiRecordMapper.selectzitiRecordByRecordId(recordId);
    }

    /**
     * 查询自提订单列表
     * 
     * @param zitiRecord 自提订单
     * @return 自提订单
     */
    @Override
    public List<zitiRecord> selectzitiRecordList(zitiRecord zitiRecord)
    {
        return zitiRecordMapper.selectzitiRecordList(zitiRecord);
    }

    /**
     * 新增自提订单
     * 
     * @param zitiRecord 自提订单
     * @return 结果
     */
    @Override
    public int insertzitiRecord(zitiRecord zitiRecord)
    {
        return zitiRecordMapper.insertzitiRecord(zitiRecord);
    }

    /**
     * 修改自提订单
     * 
     * @param zitiRecord 自提订单
     * @return 结果
     */
    @Override
    public int updatezitiRecord(zitiRecord zitiRecord)
    {
        return zitiRecordMapper.updatezitiRecord(zitiRecord);
    }

    /**
     * 批量删除自提订单
     * 
     * @param recordIds 需要删除的自提订单主键
     * @return 结果
     */
    @Override
    public int deletezitiRecordByRecordIds(Long[] recordIds)
    {
        return zitiRecordMapper.deletezitiRecordByRecordIds(recordIds);
    }

    /**
     * 删除自提订单信息
     * 
     * @param recordId 自提订单主键
     * @return 结果
     */
    @Override
    public int deletezitiRecordByRecordId(Long recordId)
    {
        return zitiRecordMapper.deletezitiRecordByRecordId(recordId);
    }
}
