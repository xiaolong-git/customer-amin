import request from '@/utils/request'

// 根据用户查询采购订单列表
export function listPurchaseorderByUser(query) {
  return request({
    url: '/business/purchaseorder/listByUser',
    method: 'get',
    params: query
  })
}

// 查询采购订单列表
export function listPurchaseorder(query) {
  return request({
    url: '/business/purchaseorder/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单详细
export function getPurchaseorder(id) {
  return request({
    url: '/business/purchaseorder/' + id,
    method: 'get'
  })
}

// 新增采购订单
export function addPurchaseorder(data) {
  return request({
    url: '/business/purchaseorder',
    method: 'post',
    data: data
  })
}

// 修改采购订单
export function updatePurchaseorder(data) {
  return request({
    url: '/business/purchaseorder',
    method: 'put',
    data: data
  })
}

// 删除采购订单
export function delPurchaseorder(id) {
  return request({
    url: '/business/purchaseorder/' + id,
    method: 'delete'
  })
}

// 导出采购订单
export function exportPurchaseorder(query) {
  return request({
    url: '/business/purchaseorder/export',
    method: 'get',
    params: query
  })
}

// 更改订单状态
export function updateStatusPurchaseorder(data) {
  return request({
    url: '/business/purchaseorder/updateStatus',
    method: 'post',
    data: data
  })
}
