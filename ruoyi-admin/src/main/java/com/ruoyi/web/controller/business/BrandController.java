package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.common.core.domain.entity.Brand;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.IBrandService;
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
 * 商品品牌表Controller
 * 
 * @author xl
 * @date 2021-04-06
 */
@RestController
@RequestMapping("/business/brand")
public class BrandController extends BaseController
{
    @Autowired
    private IBrandService brandService;

    /**
     * 查询商品品牌表列表
     */
    /*@PreAuthorize("@ss.hasPermi('business:brand:list')")*/
    @GetMapping("/list")
    public TableDataInfo list(Brand brand)
    {
        startPage();
        List<Brand> list = brandService.selectBrandList(brand);
        return getDataTable(list);
    }

    /**
     * 导出商品品牌表列表
     */
    /*@PreAuthorize("@ss.hasPermi('business:brand:export')")*/
    @Log(title = "商品品牌表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Brand brand)
    {
        List<Brand> list = brandService.selectBrandList(brand);
        ExcelUtil<Brand> util = new ExcelUtil<Brand>(Brand.class);
        return util.exportExcel(list, "brand");
    }

    /**
     * 获取商品品牌表详细信息
     */
    /*@PreAuthorize("@ss.hasPermi('business:brand:query')")*/
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(brandService.selectBrandById(id));
    }

    /**
     * 新增商品品牌表
     */
    /*@PreAuthorize("@ss.hasPermi('business:brand:add')")*/
    @Log(title = "商品品牌表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Brand brand)
    {
        return toAjax(brandService.insertBrand(brand));
    }

    /**
     * 修改商品品牌表
     */
    /*@PreAuthorize("@ss.hasPermi('business:brand:edit')")*/
    @Log(title = "商品品牌表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Brand brand)
    {
        return toAjax(brandService.updateBrand(brand));
    }

    /**
     * 删除商品品牌表
     */
    /*@PreAuthorize("@ss.hasPermi('business:brand:remove')")*/
    @Log(title = "商品品牌表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(brandService.deleteBrandByIds(ids));
    }
}
