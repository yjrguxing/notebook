package com.psyduck.notebook.content.service;

import java.util.List;
import com.psyduck.notebook.content.domain.MenuCategroy;

/**
 * 多级菜单Service接口
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
public interface IMenuCategroyService 
{
    /**
     * 查询多级菜单
     * 
     * @param id 多级菜单主键
     * @return 多级菜单
     */
    public MenuCategroy selectMenuCategroyById(String id);

    /**
     * 查询多级菜单列表
     * 
     * @param menuCategroy 多级菜单
     * @return 多级菜单集合
     */
    public List<MenuCategroy> selectMenuCategroyList(MenuCategroy menuCategroy);

    /**
     * 新增多级菜单
     * 
     * @param menuCategroy 多级菜单
     * @return 结果
     */
    public int insertMenuCategroy(MenuCategroy menuCategroy);

    /**
     * 修改多级菜单
     * 
     * @param menuCategroy 多级菜单
     * @return 结果
     */
    public int updateMenuCategroy(MenuCategroy menuCategroy);

    /**
     * 批量删除多级菜单
     * 
     * @param ids 需要删除的多级菜单主键集合
     * @return 结果
     */
    public int deleteMenuCategroyByIds(String[] ids);

    /**
     * 删除多级菜单信息
     * 
     * @param id 多级菜单主键
     * @return 结果
     */
    public int deleteMenuCategroyById(String id);

    /**
     * 恢复多级菜单信息
     *
     * @param id 多级菜单主键
     * @return 结果
     */
    public int recoverMenuCategroyByIds(String[] ids);
}
