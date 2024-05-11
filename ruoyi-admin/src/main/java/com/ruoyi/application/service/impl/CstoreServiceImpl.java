package com.ruoyi.application.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.application.mapper.CstoreMapper;
import com.ruoyi.application.domain.Cstore;
import com.ruoyi.application.service.ICstoreService;

/**
 * 商家提交申请Service业务层处理
 * 
 * @author xuguowei
 * @date 2024-05-11
 */
@Service
public class CstoreServiceImpl implements ICstoreService 
{
    @Autowired
    private CstoreMapper cstoreMapper;

    /**
     * 查询商家提交申请
     * 
     * @param cstoreId 商家提交申请主键
     * @return 商家提交申请
     */
    @Override
    public Cstore selectCstoreByCstoreId(Long cstoreId)
    {
        return cstoreMapper.selectCstoreByCstoreId(cstoreId);
    }

    /**
     * 查询商家提交申请列表
     * 
     * @param cstore 商家提交申请
     * @return 商家提交申请
     */
    @Override
    public List<Cstore> selectCstoreList(Cstore cstore)
    {
        return cstoreMapper.selectCstoreList(cstore);
    }

    /**
     * 新增商家提交申请
     * 
     * @param cstore 商家提交申请
     * @return 结果
     */
    @Override
    public int insertCstore(Cstore cstore)
    {
        return cstoreMapper.insertCstore(cstore);
    }

    /**
     * 修改商家提交申请
     * 
     * @param cstore 商家提交申请
     * @return 结果
     */
    @Override
    public int updateCstore(Cstore cstore)
    {
        return cstoreMapper.updateCstore(cstore);
    }

    /**
     * 批量删除商家提交申请
     * 
     * @param cstoreIds 需要删除的商家提交申请主键
     * @return 结果
     */
    @Override
    public int deleteCstoreByCstoreIds(Long[] cstoreIds)
    {
        return cstoreMapper.deleteCstoreByCstoreIds(cstoreIds);
    }

    /**
     * 删除商家提交申请信息
     * 
     * @param cstoreId 商家提交申请主键
     * @return 结果
     */
    @Override
    public int deleteCstoreByCstoreId(Long cstoreId)
    {
        return cstoreMapper.deleteCstoreByCstoreId(cstoreId);
    }

    /**
     * 同意商家申请信息 把status改为1，并授予角色骑商家权限
     *
     * @param cstoreIds 商家申请主键
     * @return 结果
     */
    @Override
    public int agreeCstoreByCstoreIds(Long[] cstoreIds)
    {
        return cstoreMapper.agreeCstoreByCstoreIds(cstoreIds);
    }

    /**
     * 拒绝商家申请信息 把status改为2
     *
     * @param cstoreIds 商家申请主键
     * @return 结果
     */
    @Override
    public int refuseCstoreByCstoreIds(Long[] cstoreIds)
    {
        return cstoreMapper.refuseCstoreByCstoreIds(cstoreIds);
    }

    /**
     * 查询申请的uId
     *
     * @param cstoreIds
     * @return uIds
     */
    @Override
    public Long[] selectUIdsByCstoreIds(Long[] cstoreIds)
    {
        return cstoreMapper.selectUIdsByCstoreIds(cstoreIds);
    }
}
