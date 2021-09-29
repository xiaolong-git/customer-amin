package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.PurchaseOrderContractRelation;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.PurchaseOrderContractRelationMapper;
import com.ruoyi.system.service.IPurchaseOrderContractRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购订单合同关联表Service业务层处理
 * 
 * @author xl
 * @date 2021-04-06
 */
@Service
public class PurchaseOrderContractRelationServiceImpl implements IPurchaseOrderContractRelationService
{
    @Autowired
    private PurchaseOrderContractRelationMapper purchaseOrderContractRelationMapper;

    /**
     * 查询采购订单合同关联表
     * 
     * @param id 采购订单合同关联表ID
     * @return 采购订单合同关联表
     */
    @Override
    public PurchaseOrderContractRelation selectPurchaseOrderContractRelationById(Long id)
    {
        return purchaseOrderContractRelationMapper.selectPurchaseOrderContractRelationById(id);
    }

    /**
     * 查询采购订单合同关联表列表
     * 
     * @param purchaseOrderContractRelation 采购订单合同关联表
     * @return 采购订单合同关联表
     */
    @Override
    public List<PurchaseOrderContractRelation> selectPurchaseOrderContractRelationList(PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        return purchaseOrderContractRelationMapper.selectPurchaseOrderContractRelationList(purchaseOrderContractRelation);
    }

    /**
     * 新增采购订单合同关联表
     * 
     * @param purchaseOrderContractRelation 采购订单合同关联表
     * @return 结果
     */
    @Override
    public int insertPurchaseOrderContractRelation(PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        purchaseOrderContractRelation.setCreateTime(DateUtils.getNowDate());
        return purchaseOrderContractRelationMapper.insertPurchaseOrderContractRelation(purchaseOrderContractRelation);
    }

    /**
     * 修改采购订单合同关联表
     * 
     * @param purchaseOrderContractRelation 采购订单合同关联表
     * @return 结果
     */
    @Override
    public int updatePurchaseOrderContractRelation(PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        purchaseOrderContractRelation.setUpdateTime(DateUtils.getNowDate());
        return purchaseOrderContractRelationMapper.updatePurchaseOrderContractRelation(purchaseOrderContractRelation);
    }

    /**
     * 批量删除采购订单合同关联表
     * 
     * @param ids 需要删除的采购订单合同关联表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePurchaseOrderContractRelationByIds(Long[] ids)
    {
        return purchaseOrderContractRelationMapper.deletePurchaseOrderContractRelationByIds(ids);
    }

    /**
     * 删除采购订单合同关联表信息
     * 
     * @param id 采购订单合同关联表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePurchaseOrderContractRelationById(Long id)
    {
        return purchaseOrderContractRelationMapper.deletePurchaseOrderContractRelationById(id);
    }
}
