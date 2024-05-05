package com.ruoyi.application.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.application.mapper.CriderMapper;
import com.ruoyi.application.domain.Crider;
import com.ruoyi.application.service.ICriderService;

/**
 * 骑手申请Service业务层处理
 * 
 * @author xuguowei
 * @date 2024-05-04
 */
@Service
public class CriderServiceImpl implements ICriderService 
{
    @Autowired
    private CriderMapper criderMapper;

    /**
     * 查询骑手申请
     * 
     * @param criderId 骑手申请主键
     * @return 骑手申请
     */
    @Override
    public Crider selectCriderByCriderId(Long criderId)
    {
        return criderMapper.selectCriderByCriderId(criderId);
    }

    /**
     * 查询骑手申请列表
     * 
     * @param crider 骑手申请
     * @return 骑手申请
     */
    @Override
    public List<Crider> selectCriderList(Crider crider)
    {
        return criderMapper.selectCriderList(crider);
    }

    /**
     * 新增骑手申请
     * 
     * @param crider 骑手申请
     * @return 结果
     */
    @Override
    public int insertCrider(Crider crider)
    {
        return criderMapper.insertCrider(crider);
    }

    /**
     * 修改骑手申请
     * 
     * @param crider 骑手申请
     * @return 结果
     */
    @Override
    public int updateCrider(Crider crider)
    {
        return criderMapper.updateCrider(crider);
    }

    /**
     * 批量删除骑手申请
     * 
     * @param criderIds 需要删除的骑手申请主键
     * @return 结果
     */
    @Override
    public int deleteCriderByCriderIds(Long[] criderIds)
    {
        return criderMapper.deleteCriderByCriderIds(criderIds);
    }

    /**
     * 删除骑手申请信息
     * 
     * @param criderId 骑手申请主键
     * @return 结果
     */
    @Override
    public int deleteCriderByCriderId(Long criderId)
    {
        return criderMapper.deleteCriderByCriderId(criderId);
    }


    /**
     * 同意骑手申请
     *
     * @param criderIds 骑手申请主键
     * @return 结果
     */
    @Override
    public int agreeCriderByCriderIds(Long[] criderIds)
    {
        return criderMapper.agreeCriderByCriderIds(criderIds);
    }

    /**
     * 拒绝骑手申请
     *
     * @param criderIds 骑手申请主键
     * @return 结果
     */
    @Override
    public int refuseCriderByCriderIds(Long[] criderIds)
    {
        return criderMapper.refuseCriderByCriderIds(criderIds);
    }

    /**
     * 根据criderIds查询用户ids
     *
     * @param criderIds 骑手申请主键
     * @return uIds
     */
    @Override
    public Long[] selectUIdsByCriderIds(Long[] criderIds)
    {
        return criderMapper.selectUIdsByCriderIds(criderIds);
    }


}
