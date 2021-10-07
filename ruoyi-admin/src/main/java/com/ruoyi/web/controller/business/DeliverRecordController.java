package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.common.core.domain.entity.DeliverRecord;
import com.ruoyi.system.service.IDeliverRecordService;
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
 * 发货记录表Controller
 * 
 * @author xl
 * @date 2021-07-15
 */
@RestController
@RequestMapping("/deliver/deliver")
public class DeliverRecordController extends BaseController
{
    @Autowired
    private IDeliverRecordService deliverRecordService;

    /**
     * 查询发货记录表列表
     */
    /*@PreAuthorize("@ss.hasPermi('deliver:deliver:list')")*/
    @GetMapping("/list")
    public TableDataInfo list(DeliverRecord deliverRecord)
    {
        startPage();
        List<DeliverRecord> list = deliverRecordService.selectDeliverRecordList(deliverRecord);
        return getDataTable(list);
    }

    /**
     * 导出发货记录表列表
     */
    /*@PreAuthorize("@ss.hasPermi('deliver:deliver:export')")*/
    @Log(title = "发货记录表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DeliverRecord deliverRecord)
    {
        List<DeliverRecord> list = deliverRecordService.selectDeliverRecordList(deliverRecord);
        ExcelUtil<DeliverRecord> util = new ExcelUtil<DeliverRecord>(DeliverRecord.class);
        return util.exportExcel(list, "deliver");
    }

    /**
     * 获取发货记录表详细信息
     */
    /*@PreAuthorize("@ss.hasPermi('deliver:deliver:query')")*/
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(deliverRecordService.selectDeliverRecordById(id));
    }

    /**
     * 新增发货记录表
     */
    /*@PreAuthorize("@ss.hasPermi('deliver:deliver:add')")*/
    @Log(title = "发货记录表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeliverRecord deliverRecord)
    {
        return toAjax(deliverRecordService.insertDeliverRecord(deliverRecord));
    }

    /**
     * 修改发货记录表
     */
    /*@PreAuthorize("@ss.hasPermi('deliver:deliver:edit')")*/
    @Log(title = "发货记录表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeliverRecord deliverRecord)
    {
        return toAjax(deliverRecordService.updateDeliverRecord(deliverRecord));
    }

    /**
     * 删除发货记录表
     */
    /*@PreAuthorize("@ss.hasPermi('deliver:deliver:remove')")*/
    @Log(title = "发货记录表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(deliverRecordService.deleteDeliverRecordByIds(ids));
    }
}
