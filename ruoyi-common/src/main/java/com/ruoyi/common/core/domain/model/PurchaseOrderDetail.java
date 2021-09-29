package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import lombok.Data;

import java.util.List;

/**
 * 商品实体
 */
@Data
public class PurchaseOrderDetail extends PurchaseOrder {

    //商品详情
    private List<BrandProductVo> productItems;

}
