package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.PurchaseOrderContractRelation;

import java.util.List;

/**
 * 采购订单合同关联表Mapper接口
 * 
 * @author xl
 * @date 2021-04-06
 */
public interface PurchaseOrderContractRelationMapper 
{
    /**
     * 查询采购订单合同关联表
     * 
     * @param id 采购订单合同关联表ID
     * @return 采购订单合同关联表
     */
    public PurchaseOrderContractRelation selectPurchaseOrderContractRelationById(Long id);

    /**
     * 查询采购订单合同关联表列表
     * 
     * @param purchaseOrderContractRelation 采购订单合同关联表
     * @return 采购订单合同关联表集合
     */
    public List<PurchaseOrderContractRelation> selectPurchaseOrderContractRelationList(PurchaseOrderContractRelation purchaseOrderContractRelation);

    /**
     * 新增采购订单合同关联表
     * 
     * @param purchaseOrderContractRelation 采购订单合同关联表
     * @return 结果
     */
    public int insertPurchaseOrderContractRelation(PurchaseOrderContractRelation purchaseOrderContractRelation);

    /**
     * 修改采购订单合同关联表
     * 
     * @param purchaseOrderContractRelation 采购订单合同关联表
     * @return 结果
     */
    public int updatePurchaseOrderContractRelation(PurchaseOrderContractRelation purchaseOrderContractRelation);

    /**
     * 删除采购订单合同关联表
     * 
     * @param id 采购订单合同关联表ID
     * @return 结果
     */
    public int deletePurchaseOrderContractRelationById(Long id);

    /**
     * 批量删除采购订单合同关联表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderContractRelationByIds(Long[] ids);
}
