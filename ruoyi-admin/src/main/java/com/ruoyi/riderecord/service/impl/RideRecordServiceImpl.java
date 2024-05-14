package com.ruoyi.riderecord.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.riderecord.mapper.RideRecordMapper;
import com.ruoyi.riderecord.domain.RideRecord;
import com.ruoyi.riderecord.service.IRideRecordService;

/**
 * 跑腿Service业务层处理
 * 
 * @author wj
 * @date 2024-05-13
 */
@Service
public class RideRecordServiceImpl implements IRideRecordService 
{
    @Autowired
    private RideRecordMapper rideRecordMapper;

    /**
     * 查询跑腿
     * 
     * @param recordId 跑腿主键
     * @return 跑腿
     */
    @Override
    public RideRecord selectRideRecordByRecordId(Long recordId)
    {
        return rideRecordMapper.selectRideRecordByRecordId(recordId);
    }

    /**
     * 查询跑腿列表
     * 
     * @param rideRecord 跑腿
     * @return 跑腿
     */
    @Override
    public List<RideRecord> selectRideRecordList(RideRecord rideRecord)
    {
        return rideRecordMapper.selectRideRecordList(rideRecord);
    }

    /**
     * 新增跑腿
     * 
     * @param rideRecord 跑腿
     * @return 结果
     */
    @Override
    public int insertRideRecord(RideRecord rideRecord)
    {
        return rideRecordMapper.insertRideRecord(rideRecord);
    }

    /**
     * 修改跑腿
     * 
     * @param rideRecord 跑腿
     * @return 结果
     */
    @Override
    public int updateRideRecord(RideRecord rideRecord)
    {
        return rideRecordMapper.updateRideRecord(rideRecord);
    }

    /**
     * 批量删除跑腿
     * 
     * @param recordIds 需要删除的跑腿主键
     * @return 结果
     */
    @Override
    public int deleteRideRecordByRecordIds(Long[] recordIds)
    {
        return rideRecordMapper.deleteRideRecordByRecordIds(recordIds);
    }

    /**
     * 删除跑腿信息
     * 
     * @param recordId 跑腿主键
     * @return 结果
     */
    @Override
    public int deleteRideRecordByRecordId(Long recordId)
    {
        return rideRecordMapper.deleteRideRecordByRecordId(recordId);
    }
}
