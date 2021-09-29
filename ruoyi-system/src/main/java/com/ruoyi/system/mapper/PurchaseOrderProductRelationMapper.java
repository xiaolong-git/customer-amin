package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.PurchaseOrderProductRelation;

import java.util.List;

/**
 * 采购订单商品关联表Mapper接口
 * 
 * @author xl
 * @date 2021-04-06
 */
public interface PurchaseOrderProductRelationMapper 
{
    /**
     * 查询采购订单商品关联表
     * 
     * @param id 采购订单商品关联表ID
     * @return 采购订单商品关联表
     */
    public PurchaseOrderProductRelation selectPurchaseOrderProductRelationById(Long id);

    /**
     * 查询采购订单商品关联表列表
     * 
     * @param purchaseOrderProductRelation 采购订单商品关联表
     * @return 采购订单商品关联表集合
     */
    public List<PurchaseOrderProductRelation> selectPurchaseOrderProductRelationList(PurchaseOrderProductRelation purchaseOrderProductRelation);

    /**
     * 新增采购订单商品关联表
     * 
     * @param purchaseOrderProductRelation 采购订单商品关联表
     * @return 结果
     */
    public int insertPurchaseOrderProductRelation(PurchaseOrderProductRelation purchaseOrderProductRelation);

    /**
     * 修改采购订单商品关联表
     * 
     * @param purchaseOrderProductRelation 采购订单商品关联表
     * @return 结果
     */
    public int updatePurchaseOrderProductRelation(PurchaseOrderProductRelation purchaseOrderProductRelation);

    /**
     * 删除采购订单商品关联表
     * 
     * @param id 采购订单商品关联表ID
     * @return 结果
     */
    public int deletePurchaseOrderProductRelationById(Long id);

    /**
     * 删除采购订单商品关联表
     *
     * @param orderId 采购订单ID
     * @return 结果
     */
    public int deletePurchaseOrderProductRelationByOrderId(Long orderId);

    /**
     * 批量删除采购订单商品关联表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderProductRelationByIds(Long[] ids);
}
