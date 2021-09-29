package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.PurchaseOrderProductRelation;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.PurchaseOrderProductRelationMapper;
import com.ruoyi.system.service.IPurchaseOrderProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购订单商品关联表Service业务层处理
 * 
 * @author xl
 * @date 2021-04-06
 */
@Service
public class PurchaseOrderProductRelationServiceImpl implements IPurchaseOrderProductRelationService
{
    @Autowired
    private PurchaseOrderProductRelationMapper purchaseOrderProductRelationMapper;

    /**
     * 查询采购订单商品关联表
     * 
     * @param id 采购订单商品关联表ID
     * @return 采购订单商品关联表
     */
    @Override
    public PurchaseOrderProductRelation selectPurchaseOrderProductRelationById(Long id)
    {
        return purchaseOrderProductRelationMapper.selectPurchaseOrderProductRelationById(id);
    }

    /**
     * 查询采购订单商品关联表列表
     * 
     * @param purchaseOrderProductRelation 采购订单商品关联表
     * @return 采购订单商品关联表
     */
    @Override
    public List<PurchaseOrderProductRelation> selectPurchaseOrderProductRelationList(PurchaseOrderProductRelation purchaseOrderProductRelation)
    {
        return purchaseOrderProductRelationMapper.selectPurchaseOrderProductRelationList(purchaseOrderProductRelation);
    }

    /**
     * 新增采购订单商品关联表
     * 
     * @param purchaseOrderProductRelation 采购订单商品关联表
     * @return 结果
     */
    @Override
    public int insertPurchaseOrderProductRelation(PurchaseOrderProductRelation purchaseOrderProductRelation)
    {
        purchaseOrderProductRelation.setCreateTime(DateUtils.getNowDate());
        return purchaseOrderProductRelationMapper.insertPurchaseOrderProductRelation(purchaseOrderProductRelation);
    }

    /**
     * 修改采购订单商品关联表
     * 
     * @param purchaseOrderProductRelation 采购订单商品关联表
     * @return 结果
     */
    @Override
    public int updatePurchaseOrderProductRelation(PurchaseOrderProductRelation purchaseOrderProductRelation)
    {
        purchaseOrderProductRelation.setUpdateTime(DateUtils.getNowDate());
        return purchaseOrderProductRelationMapper.updatePurchaseOrderProductRelation(purchaseOrderProductRelation);
    }

    /**
     * 批量删除采购订单商品关联表
     * 
     * @param ids 需要删除的采购订单商品关联表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePurchaseOrderProductRelationByIds(Long[] ids)
    {
        return purchaseOrderProductRelationMapper.deletePurchaseOrderProductRelationByIds(ids);
    }

    /**
     * 删除采购订单商品关联表信息
     * 
     * @param id 采购订单商品关联表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePurchaseOrderProductRelationById(Long id)
    {
        return purchaseOrderProductRelationMapper.deletePurchaseOrderProductRelationById(id);
    }
}
