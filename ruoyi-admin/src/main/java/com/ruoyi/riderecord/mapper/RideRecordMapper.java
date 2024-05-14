package com.ruoyi.riderecord.mapper;

import java.util.List;
import com.ruoyi.riderecord.domain.RideRecord;

/**
 * 跑腿Mapper接口
 * 
 * @author wj
 * @date 2024-05-13
 */
public interface RideRecordMapper 
{
    /**
     * 查询跑腿
     * 
     * @param recordId 跑腿主键
     * @return 跑腿
     */
    public RideRecord selectRideRecordByRecordId(Long recordId);

    /**
     * 查询跑腿列表
     * 
     * @param rideRecord 跑腿
     * @return 跑腿集合
     */
    public List<RideRecord> selectRideRecordList(RideRecord rideRecord);

    /**
     * 新增跑腿
     * 
     * @param rideRecord 跑腿
     * @return 结果
     */
    public int insertRideRecord(RideRecord rideRecord);

    /**
     * 修改跑腿
     * 
     * @param rideRecord 跑腿
     * @return 结果
     */
    public int updateRideRecord(RideRecord rideRecord);

    /**
     * 删除跑腿
     * 
     * @param recordId 跑腿主键
     * @return 结果
     */
    public int deleteRideRecordByRecordId(Long recordId);

    /**
     * 批量删除跑腿
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRideRecordByRecordIds(Long[] recordIds);
}
