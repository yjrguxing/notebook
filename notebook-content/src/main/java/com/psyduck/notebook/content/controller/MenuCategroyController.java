package com.psyduck.notebook.content.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.psyduck.notebook.common.annotation.Log;
import com.psyduck.notebook.common.core.controller.BaseController;
import com.psyduck.notebook.common.core.domain.AjaxResult;
import com.psyduck.notebook.common.enums.BusinessType;
import com.psyduck.notebook.content.domain.MenuCategroy;
import com.psyduck.notebook.content.service.IMenuCategroyService;
import com.psyduck.notebook.common.utils.poi.ExcelUtil;
import com.psyduck.notebook.common.core.page.TableDataInfo;

/**
 * 多级菜单Controller
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/menu/categroy")
public class MenuCategroyController extends BaseController
{
    @Autowired
    private IMenuCategroyService menuCategroyService;

    /**
     * 查询多级菜单列表
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:list')")
    @GetMapping("/list")
    public TableDataInfo list(MenuCategroy menuCategroy)
    {
        startPage();
        List<MenuCategroy> list = menuCategroyService.selectMenuCategroyList(menuCategroy);
        return getDataTable(list);
    }

    /**
     * 导出多级菜单列表
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:export')")
    @Log(title = "多级菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MenuCategroy menuCategroy)
    {
        List<MenuCategroy> list = menuCategroyService.selectMenuCategroyList(menuCategroy);
        ExcelUtil<MenuCategroy> util = new ExcelUtil<MenuCategroy>(MenuCategroy.class);
        util.exportExcel(response, list, "多级菜单数据");
    }

    /**
     * 获取多级菜单详细信息
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(menuCategroyService.selectMenuCategroyById(id));
    }

    /**
     * 新增多级菜单
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:add')")
    @Log(title = "多级菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MenuCategroy menuCategroy)
    {
        return toAjax(menuCategroyService.insertMenuCategroy(menuCategroy));
    }

    /**
     * 修改多级菜单
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:edit')")
    @Log(title = "多级菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MenuCategroy menuCategroy)
    {
        return toAjax(menuCategroyService.updateMenuCategroy(menuCategroy));
    }

    /**
     * 删除多级菜单
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:remove')")
    @Log(title = "多级菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(menuCategroyService.deleteMenuCategroyByIds(ids));
    }

    /**
     * 恢复多级菜单
     */
    @PreAuthorize("@ss.hasPermi('menu:categroy:recovery')")
    @Log(title = "多级菜单", businessType = BusinessType.RECOVER)
    @PutMapping("/recover/{ids}")
    public AjaxResult recover(@PathVariable String[] ids)
    {
        return toAjax(menuCategroyService.recoverMenuCategroyByIds(ids));
    }
}
