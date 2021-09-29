package com.ruoyi.system.mapper;


import java.util.List;
import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import com.ruoyi.common.core.domain.model.PurchaseOrderReqVo;

/**
 * 采购订单Mapper接口
 *
 * @author xl
 * @date 2021-03-31
 */
public interface PurchaseOrderMapper
{
    /**
     * 查询采购订单
     *
     * @param id 采购订单ID
     * @return 采购订单
     */
    public PurchaseOrder selectPurchaseOrderById(Long id);

    /**
     * 查询采购订单列表
     *
     * @param purchaseOrder 采购订单
     * @return 采购订单集合
     */
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrderReqVo purchaseOrder);

    /**
     * 新增采购订单
     *
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 修改采购订单
     *
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 删除采购订单
     *
     * @param id 采购订单ID
     * @return 结果
     */
    public int deletePurchaseOrderById(Long id);

    /**
     * 批量删除采购订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderByIds(Long[] ids);
}