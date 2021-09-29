package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderReqVo extends PurchaseOrder {

    //状态集合
    private List<String> statusList;

}
