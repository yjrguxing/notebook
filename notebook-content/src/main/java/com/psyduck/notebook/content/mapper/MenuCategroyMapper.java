package com.psyduck.notebook.content.mapper;

import java.util.List;
import com.psyduck.notebook.content.domain.MenuCategroy;

/**
 * 多级菜单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
public interface MenuCategroyMapper 
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
     * 删除多级菜单
     * 
     * @param id 多级菜单主键
     * @return 结果
     */
    public int deleteMenuCategroyById(String id);

    /**
     * 批量删除多级菜单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMenuCategroyByIds(String[] ids);

    /**
     * 恢复多级菜单
     *
     * @param ids 多级菜单主键
     * @return 结果
     */
    public int recoverMenuCategroyById(String[] ids);
}
