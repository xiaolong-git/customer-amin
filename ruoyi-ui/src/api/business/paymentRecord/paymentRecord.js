import request from '@/utils/request'

// 查询回款记录表列表
export function listPaymentRecord(query) {
  return request({
    url: '/business/paymentRecord/list',
    method: 'get',
    params: query
  })
}

// 查询回款记录表详细
export function getPaymentRecord(id) {
  return request({
    url: '/business/paymentRecord/' + id,
    method: 'get'
  })
}

// 新增回款记录表
export function addPaymentRecord(data) {
  return request({
    url: '/business/paymentRecord',
    method: 'post',
    data: data
  })
}

// 修改回款记录表
export function updatePaymentRecord(data) {
  return request({
    url: '/business/paymentRecord',
    method: 'put',
    data: data
  })
}

// 删除回款记录表
export function delPaymentRecord(id) {
  return request({
    url: '/business/paymentRecord/' + id,
    method: 'delete'
  })
}

// 导出回款记录表
export function exportPaymentRecord(query) {
  return request({
    url: '/business/paymentRecord/export',
    method: 'get',
    params: query
  })
}
