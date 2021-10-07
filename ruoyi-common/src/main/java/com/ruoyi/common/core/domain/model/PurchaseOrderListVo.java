package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import lombok.Data;

@Data
public class PurchaseOrderListVo extends PurchaseOrder {

    /**
     * 剩余发货数量 kg
     */
    private Integer surplusDeliver;

}
