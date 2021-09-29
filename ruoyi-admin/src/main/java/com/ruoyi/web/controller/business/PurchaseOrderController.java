package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.common.core.domain.entity.PurchaseOrder;
import com.ruoyi.common.core.domain.model.PurchaseOrderDetail;
import com.ruoyi.common.core.domain.model.PurchaseOrderReqVo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.IPurchaseOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购订单Controller
 *
 * @author xl
 * @date 2021-03-31
 */
@RestController
@RequestMapping("/business/purchaseorder")
public class PurchaseOrderController extends BaseController
{
    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    /**
     * 查询采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:listByUser')")
    @GetMapping("/listByUser")
    public TableDataInfo listByUser(PurchaseOrderReqVo purchaseOrder)
    {
        startPage();
        purchaseOrder.setCreateBy(SecurityUtils.getUsername());
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        return getDataTable(list);
    }

    /**
     * 查询采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrderReqVo purchaseOrder)
    {
        startPage();
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        return getDataTable(list);
    }

    /**
     * 导出采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:export')")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseOrderReqVo purchaseOrder)
    {
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        ExcelUtil<PurchaseOrder> util = new ExcelUtil<PurchaseOrder>(PurchaseOrder.class);
        return util.exportExcel(list, "purchaseorder");
    }

    /**
     * 获取采购订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(purchaseOrderService.selectPurchaseOrderById(id));
    }

    /**
     * 新增采购订单
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:add')")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrderDetail purchaseOrder)
    {
        return toAjax(purchaseOrderService.insertPurchaseOrder(purchaseOrder));
    }

    /**
     * 新增采购订单
     */
    /*@PreAuthorize("@ss.hasPermi('business:purchaseorder:updateStatus')")*/
    @Log(title = "更改订单状态", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody PurchaseOrderDetail purchaseOrder)
    {
        return toAjax(purchaseOrderService.updateStatus(purchaseOrder));
    }

    /**
     * 修改采购订单
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:edit')")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrderDetail purchaseOrder)
    {
        purchaseOrder.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(purchaseOrderService.updatePurchaseOrder(purchaseOrder));
    }

    /**
     * 删除采购订单
     */
    @PreAuthorize("@ss.hasPermi('business:purchaseorder:remove')")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(purchaseOrderService.deletePurchaseOrderByIds(ids));
    }
}
