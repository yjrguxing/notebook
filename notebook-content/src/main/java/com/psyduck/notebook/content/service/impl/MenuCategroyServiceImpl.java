package com.psyduck.notebook.content.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psyduck.notebook.content.mapper.MenuCategroyMapper;
import com.psyduck.notebook.content.domain.MenuCategroy;
import com.psyduck.notebook.content.service.IMenuCategroyService;

/**
 * 多级菜单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
@Service
public class MenuCategroyServiceImpl implements IMenuCategroyService 
{
    @Autowired
    private MenuCategroyMapper menuCategroyMapper;

    /**
     * 查询多级菜单
     * 
     * @param id 多级菜单主键
     * @return 多级菜单
     */
    @Override
    public MenuCategroy selectMenuCategroyById(String id)
    {
        return menuCategroyMapper.selectMenuCategroyById(id);
    }

    /**
     * 查询多级菜单列表
     * 
     * @param menuCategroy 多级菜单
     * @return 多级菜单
     */
    @Override
    public List<MenuCategroy> selectMenuCategroyList(MenuCategroy menuCategroy)
    {
        return menuCategroyMapper.selectMenuCategroyList(menuCategroy);
    }

    /**
     * 新增多级菜单
     * 
     * @param menuCategroy 多级菜单
     * @return 结果
     */
    @Override
    public int insertMenuCategroy(MenuCategroy menuCategroy)
    {
        return menuCategroyMapper.insertMenuCategroy(menuCategroy);
    }

    /**
     * 修改多级菜单
     * 
     * @param menuCategroy 多级菜单
     * @return 结果
     */
    @Override
    public int updateMenuCategroy(MenuCategroy menuCategroy)
    {
        return menuCategroyMapper.updateMenuCategroy(menuCategroy);
    }

    /**
     * 批量删除多级菜单
     * 
     * @param ids 需要删除的多级菜单主键
     * @return 结果
     */
    @Override
    public int deleteMenuCategroyByIds(String[] ids)
    {
        return menuCategroyMapper.deleteMenuCategroyByIds(ids);
    }

    /**
     * 删除多级菜单信息
     * 
     * @param id 多级菜单主键
     * @return 结果
     */
    @Override
    public int deleteMenuCategroyById(String id)
    {
        return menuCategroyMapper.deleteMenuCategroyById(id);
    }

    /**
     * 恢复多级菜单信息
     *
     * @param ids 多级菜单主键
     * @return 结果
     */
    @Override
    public int recoverMenuCategroyByIds(String[] ids)
    {
        return menuCategroyMapper.recoverMenuCategroyById(ids);
    }
}
