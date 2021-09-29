package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.PurchaseOrderContractRelation;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.system.service.IPurchaseOrderContractRelationService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购订单合同关联表Controller
 * 
 * @author xl
 * @date 2021-04-06
 */
@RestController
@RequestMapping("/business/orderContractRelation")
public class PurchaseOrderContractRelationController extends BaseController
{
    @Autowired
    private IPurchaseOrderContractRelationService purchaseOrderContractRelationService;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询采购订单合同关联表列表
     */
    /*@PreAuthorize("@ss.hasPermi('business:orderContractRelation:list')")*/
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        startPage();
        List<PurchaseOrderContractRelation> list = purchaseOrderContractRelationService.selectPurchaseOrderContractRelationList(purchaseOrderContractRelation);
        return getDataTable(list);
    }

    /**
     * 导出采购订单合同关联表列表
     */
    /*@PreAuthorize("@ss.hasPermi('business:orderContractRelation:export')")*/
    @Log(title = "采购订单合同关联表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        List<PurchaseOrderContractRelation> list = purchaseOrderContractRelationService.selectPurchaseOrderContractRelationList(purchaseOrderContractRelation);
        ExcelUtil<PurchaseOrderContractRelation> util = new ExcelUtil<PurchaseOrderContractRelation>(PurchaseOrderContractRelation.class);
        return util.exportExcel(list, "orderContractRelation");
    }

    /**
     * 获取采购订单合同关联表详细信息
     */
    /*@PreAuthorize("@ss.hasPermi('business:orderContractRelation:query')")*/
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(purchaseOrderContractRelationService.selectPurchaseOrderContractRelationById(id));
    }

    /**
     * 新增采购订单合同关联表
     */
    /*@PreAuthorize("@ss.hasPermi('business:orderContractRelation:add')")*/
    @Log(title = "采购订单合同关联表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        return toAjax(purchaseOrderContractRelationService.insertPurchaseOrderContractRelation(purchaseOrderContractRelation));
    }

    /**
     * 修改采购订单合同关联表
     */
    /*@PreAuthorize("@ss.hasPermi('business:orderContractRelation:edit')")*/
    @Log(title = "采购订单合同关联表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrderContractRelation purchaseOrderContractRelation)
    {
        return toAjax(purchaseOrderContractRelationService.updatePurchaseOrderContractRelation(purchaseOrderContractRelation));
    }

    /**
     * 删除采购订单合同关联表
     */
    /*@PreAuthorize("@ss.hasPermi('business:orderContractRelation:remove')")*/
    @Log(title = "采购订单合同关联表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(purchaseOrderContractRelationService.deletePurchaseOrderContractRelationByIds(ids));
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam(value = "file") MultipartFile[] files, @RequestParam(value = "orderId") Long orderId) throws Exception
    {
        try
        {
            if(orderId == null){
                throw new CustomException("未获取到订单详情信息");
            }

            AjaxResult ajax = AjaxResult.success();
            List<String> fileList = Lists.newArrayList();

            if(files != null && files.length>0){
                for (MultipartFile file : files) {
                    // 上传文件路径
                    String filePath = RuoYiConfig.getUploadPath();
                    // 上传并返回新文件名称
                    String fileName = FileUploadUtils.upload(filePath, file);
                    //String url = serverConfig.getUrl() + fileName;
                    fileList.add(fileName);

                    //增加合同文件关联关系
                    PurchaseOrderContractRelation purchaseOrderContractRelation = new PurchaseOrderContractRelation();
                    purchaseOrderContractRelation.setOrderId(orderId);
                    purchaseOrderContractRelation.setPic(fileName);
                    purchaseOrderContractRelationService.insertPurchaseOrderContractRelation(purchaseOrderContractRelation);
                }
            }
            ajax.put("fileNameList", fileList);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

}
