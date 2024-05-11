package com.ruoyi.application.service;

import java.util.List;
import com.ruoyi.application.domain.Crider;

/**
 * 骑手申请Service接口
 * 
 * @author xuguowei
 * @date 2024-05-04
 */
public interface ICriderService 
{
    /**
     * 查询骑手申请
     * 
     * @param criderId 骑手申请主键
     * @return 骑手申请
     */
    public Crider selectCriderByCriderId(Long criderId);

    /**
     * 查询骑手申请列表
     * 
     * @param crider 骑手申请
     * @return 骑手申请集合
     */
    public List<Crider> selectCriderList(Crider crider);

    /**
     * 新增骑手申请
     * 
     * @param crider 骑手申请
     * @return 结果
     */
    public int insertCrider(Crider crider);

    /**
     * 修改骑手申请
     * 
     * @param crider 骑手申请
     * @return 结果
     */
    public int updateCrider(Crider crider);

    /**
     * 批量删除骑手申请
     * 
     * @param criderIds 需要删除的骑手申请主键集合
     * @return 结果
     */
    public int deleteCriderByCriderIds(Long[] criderIds);

    /**
     * 删除骑手申请信息
     * 
     * @param criderId 骑手申请主键
     * @return 结果
     */
    public int deleteCriderByCriderId(Long criderId);

    /**
     * 同意骑手申请信息 把status改为1，pass改为1,并授予角色骑手权限
     *
     * @param criderIds 骑手申请主键
     * @return 结果
     */
    public int agreeCriderByCriderIds(Long[] criderIds);

    /**
     * 拒绝骑手申请信息 把status改为1，pass改为0
     *
     * @param criderIds 骑手申请主键
     * @return 结果
     */
    public int refuseCriderByCriderIds(Long[] criderIds);

    /**
     * 根据criderIds查询用户ids
     *
     * @param criderIds 骑手申请主键
     * @return uIds
     */
    public Long[] selectUIdsByCriderIds(Long[] criderIds);
}
