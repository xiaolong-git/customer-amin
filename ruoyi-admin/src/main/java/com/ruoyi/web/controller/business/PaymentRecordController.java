package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.common.core.domain.entity.PaymentRecord;
import com.ruoyi.system.service.IPaymentRecordService;
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
 * 回款记录表Controller
 * 
 * @author xl
 * @date 2021-04-06
 */
@RestController
@RequestMapping("/business/paymentRecord")
public class PaymentRecordController extends BaseController
{
    @Autowired
    private IPaymentRecordService paymentRecordService;

    /**
     * 查询回款记录表列表
     */
    /*@PreAuthorize("@ss.hasPermi('business:paymentRecord:list')")*/
    @GetMapping("/list")
    public TableDataInfo list(PaymentRecord paymentRecord)
    {
        startPage();
        List<PaymentRecord> list = paymentRecordService.selectPaymentRecordList(paymentRecord);
        return getDataTable(list);
    }

    /**
     * 导出回款记录表列表
     */
    /*@PreAuthorize("@ss.hasPermi('business:paymentRecord:export')")*/
    @Log(title = "回款记录表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PaymentRecord paymentRecord)
    {
        List<PaymentRecord> list = paymentRecordService.selectPaymentRecordList(paymentRecord);
        ExcelUtil<PaymentRecord> util = new ExcelUtil<PaymentRecord>(PaymentRecord.class);
        return util.exportExcel(list, "paymentRecord");
    }

    /**
     * 获取回款记录表详细信息
     */
    /*@PreAuthorize("@ss.hasPermi('business:paymentRecord:query')")*/
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(paymentRecordService.selectPaymentRecordById(id));
    }

    /**
     * 新增回款记录表
     */
    /*@PreAuthorize("@ss.hasPermi('business:paymentRecord:add')")*/
    @Log(title = "回款记录表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentRecord paymentRecord)
    {
        return toAjax(paymentRecordService.insertPaymentRecord(paymentRecord));
    }

    /**
     * 修改回款记录表
     */
    /*@PreAuthorize("@ss.hasPermi('business:paymentRecord:edit')")*/
    @Log(title = "回款记录表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentRecord paymentRecord)
    {
        return toAjax(paymentRecordService.updatePaymentRecord(paymentRecord));
    }

    /**
     * 删除回款记录表
     */
    /*@PreAuthorize("@ss.hasPermi('business:paymentRecord:remove')")*/
    @Log(title = "回款记录表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(paymentRecordService.deletePaymentRecordByIds(ids));
    }
}
