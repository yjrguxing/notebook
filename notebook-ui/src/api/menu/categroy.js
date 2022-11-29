import request from '@/utils/request'

// 查询多级菜单列表
export function listCategroy(query) {
  return request({
    url: '/menu/categroy/list',
    method: 'get',
    params: query
  })
}

// 查询多级菜单详细
export function getCategroy(id) {
  return request({
    url: '/menu/categroy/' + id,
    method: 'get'
  })
}

// 新增多级菜单
export function addCategroy(data) {
  return request({
    url: '/menu/categroy',
    method: 'post',
    data: data
  })
}

// 修改多级菜单
export function updateCategroy(data) {
  return request({
    url: '/menu/categroy',
    method: 'put',
    data: data
  })
}

// 删除多级菜单
export function delCategroy(id) {
  return request({
    url: '/menu/categroy/' + id,
    method: 'delete'
  })
}

// 恢复多级菜单
export function recoverCategroy(id) {
  return request({
    url: '/menu/categroy/recover/' + id,
    method: 'put'
  })
}
