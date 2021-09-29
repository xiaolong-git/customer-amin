package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.Product;
import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import com.ruoyi.common.core.domain.entity.PurchaseOrderProductRelation;
import com.ruoyi.common.core.domain.model.BrandProductVo;
import com.ruoyi.common.core.domain.model.PurchaseOrderDetail;
import com.ruoyi.common.core.domain.model.PurchaseOrderReqVo;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.mapper.ProductMapper;
import com.ruoyi.system.mapper.PurchaseOrderMapper;
import com.ruoyi.system.mapper.PurchaseOrderProductRelationMapper;
import com.ruoyi.system.service.IPurchaseOrderService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 采购订单Service业务层处理
 *
 * @author xl
 * @date 2021-03-31
 */
@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService
{
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseOrderProductRelationMapper purchaseOrderProductRelationMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询采购订单
     *
     * @param id 采购订单ID
     * @return 采购订单
     */
    @Override
    public PurchaseOrderDetail selectPurchaseOrderById(Long id)
    {
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
        PurchaseOrder order = purchaseOrderMapper.selectPurchaseOrderById(id);
        BeanUtils.copyProperties(order, purchaseOrderDetail);

        //封装订单和商品关系
        PurchaseOrderProductRelation param = new PurchaseOrderProductRelation();
        param.setOrderId(order.getId());
        List<PurchaseOrderProductRelation> productRelationList = purchaseOrderProductRelationMapper.selectPurchaseOrderProductRelationList(param);
        if(!CollectionUtils.isEmpty(productRelationList)){
            List<BrandProductVo> productVoList = Lists.newArrayList();
            for (PurchaseOrderProductRelation productRelation : productRelationList) {
                Product product = productMapper.selectProductById(productRelation.getProductId());
                BrandProductVo productVo = new BrandProductVo();
                BeanUtils.copyProperties(productRelation, productVo);
                productVo.setBrandId(product.getBrandId());
                productVoList.add(productVo);
            }
            purchaseOrderDetail.setProductItems(productVoList);
        }

        return purchaseOrderDetail;
    }

    /**
     * 查询采购订单列表
     *
     * @param purchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrderReqVo purchaseOrder)
    {
        return purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
    }

    /**
     * 新增采购订单
     *
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPurchaseOrder(PurchaseOrderDetail purchaseOrder)
    {
        List<BrandProductVo> productItems = purchaseOrder.getProductItems();
        if(CollectionUtils.isEmpty(productItems)){
            throw new CustomException("请选择订单商品");
        }

        PurchaseOrder order = new PurchaseOrder();
        BeanUtils.copyProperties(purchaseOrder, order);
        order.setCreateBy(SecurityUtils.getUsername());
        order.setCreateTime(DateUtils.getNowDate());
        purchaseOrderMapper.insertPurchaseOrder(order);
        String id = order.getId().toString();
        order.setCode(StringUtils.getTableCode("HS-", id));

        //保存订单和商品关系
        for (BrandProductVo productItem : productItems) {
            PurchaseOrderProductRelation productRelation = new PurchaseOrderProductRelation();
            BeanUtils.copyProperties(productItem, productRelation);
            productRelation.setOrderId(order.getId());
            purchaseOrderProductRelationMapper.insertPurchaseOrderProductRelation(productRelation);
        }

        return purchaseOrderMapper.updatePurchaseOrder(order);
    }

    /**
     * 修改采购订单
     *
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePurchaseOrder(PurchaseOrderDetail purchaseOrder)
    {
        List<BrandProductVo> productItems = purchaseOrder.getProductItems();
        if(CollectionUtils.isEmpty(productItems)){
            throw new CustomException("请选择订单商品");
        }

        PurchaseOrder order = new PurchaseOrder();
        BeanUtils.copyProperties(purchaseOrder, order);
        order.setUpdateTime(DateUtils.getNowDate());

        //删除订单商品关系
        purchaseOrderProductRelationMapper.deletePurchaseOrderProductRelationByOrderId(order.getId());

        //保存订单和商品关系
        for (BrandProductVo productItem : productItems) {
            PurchaseOrderProductRelation productRelation = new PurchaseOrderProductRelation();
            BeanUtils.copyProperties(productItem, productRelation);
            productRelation.setOrderId(order.getId());
            purchaseOrderProductRelationMapper.insertPurchaseOrderProductRelation(productRelation);
        }

        return purchaseOrderMapper.updatePurchaseOrder(order);
    }

    /**
     * 批量删除采购订单
     *
     * @param ids 需要删除的采购订单ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePurchaseOrderByIds(Long[] ids)
    {
        for(Long id : ids){
            checkDelPurchaseOrder(id);

            PurchaseOrderProductRelation param = new PurchaseOrderProductRelation();
            param.setOrderId(id);
            List<PurchaseOrderProductRelation> productRelations = purchaseOrderProductRelationMapper.selectPurchaseOrderProductRelationList(param);
            if(!CollectionUtils.isEmpty(productRelations)){
                for (PurchaseOrderProductRelation relation : productRelations) {
                    purchaseOrderProductRelationMapper.deletePurchaseOrderProductRelationById(relation.getId());
                }
            }
        }

        return purchaseOrderMapper.deletePurchaseOrderByIds(ids);
    }

    /**
     * 删除采购订单信息
     *
     * @param id 采购订单ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePurchaseOrderById(Long id)
    {
        checkDelPurchaseOrder(id);

        PurchaseOrderProductRelation param = new PurchaseOrderProductRelation();
        param.setOrderId(id);
        List<PurchaseOrderProductRelation> productRelations = purchaseOrderProductRelationMapper.selectPurchaseOrderProductRelationList(param);
        if(!CollectionUtils.isEmpty(productRelations)){
            for (PurchaseOrderProductRelation relation : productRelations) {
                purchaseOrderProductRelationMapper.deletePurchaseOrderProductRelationById(relation.getId());
            }
        }

        return purchaseOrderMapper.deletePurchaseOrderById(id);
    }

    /**
     * 校验是否可以删除采购订单
     * @param id
     */
    private void checkDelPurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectPurchaseOrderById(id);
        if(purchaseOrder == null){
            throw new CustomException("获取订单详情信息异常，请联系管理员");
        }

        if(!"0".equals(purchaseOrder.getStatus())){
            throw new CustomException("订单状态已发生改变，请核实后删除");
        }
    }

    /**
     * 更改订单状态
     * @param purchaseOrder
     * @return
     */
    @Override
    public int updateStatus(PurchaseOrder purchaseOrder) {
        if(purchaseOrder.getId() == null || StringUtils.isEmpty(purchaseOrder.getStatus())){
            throw new CustomException("更新订单状态异常，请联系管理员");
        }

        PurchaseOrder order = purchaseOrderMapper.selectPurchaseOrderById(purchaseOrder.getId());
        if(order == null){
            throw new CustomException("获取订单详情信息异常，请联系管理员");
        }
        order.setStatus(purchaseOrder.getStatus());
        return purchaseOrderMapper.updatePurchaseOrder(order);
    }
}