package com.ruoyi.record.service;

import java.util.List;
import com.ruoyi.record.domain.myriderecord;

/**
 * 我的跑腿订单Service接口
 * 
 * @author xpl
 * @date 2024-05-16
 */
public interface ImyriderecordService 
{
    /**
     * 查询我的跑腿订单
     * 
     * @param recordId 我的跑腿订单主键
     * @return 我的跑腿订单
     */
    public myriderecord selectmyriderecordByRecordId(Long recordId);

    /**
     * 查询我的跑腿订单列表
     * 
     * @param myriderecord 我的跑腿订单
     * @return 我的跑腿订单集合
     */
    public List<myriderecord> selectmyriderecordList(myriderecord myriderecord);

    /**
     * 新增我的跑腿订单
     * 
     * @param myriderecord 我的跑腿订单
     * @return 结果
     */
    public int insertmyriderecord(myriderecord myriderecord);

    /**
     * 修改我的跑腿订单
     * 
     * @param myriderecord 我的跑腿订单
     * @return 结果
     */
    public int updatemyriderecord(myriderecord myriderecord);

    /**
     * 批量删除我的跑腿订单
     * 
     * @param recordIds 需要删除的我的跑腿订单主键集合
     * @return 结果
     */
    public int deletemyriderecordByRecordIds(Long[] recordIds);

    /**
     * 删除我的跑腿订单信息
     * 
     * @param recordId 我的跑腿订单主键
     * @return 结果
     */
    public int deletemyriderecordByRecordId(Long recordId);
}
