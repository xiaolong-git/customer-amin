package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderReqVo extends PurchaseOrder {

    //ηΆζιε
    private List<String> statusList;

}
