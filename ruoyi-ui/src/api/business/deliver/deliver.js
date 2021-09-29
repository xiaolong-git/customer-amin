import request from '@/utils/request'

// 查询发货记录表列表
export function listDeliver(query) {
  return request({
    url: '/deliver/deliver/list',
    method: 'get',
    params: query
  })
}

// 查询发货记录表详细
export function getDeliver(id) {
  return request({
    url: '/deliver/deliver/' + id,
    method: 'get'
  })
}

// 新增发货记录表
export function addDeliver(data) {
  return request({
    url: '/deliver/deliver',
    method: 'post',
    data: data
  })
}

// 修改发货记录表
export function updateDeliver(data) {
  return request({
    url: '/deliver/deliver',
    method: 'put',
    data: data
  })
}

// 删除发货记录表
export function delDeliver(id) {
  return request({
    url: '/deliver/deliver/' + id,
    method: 'delete'
  })
}

// 导出发货记录表
export function exportDeliver(query) {
  return request({
    url: '/deliver/deliver/export',
    method: 'get',
    params: query
  })
}