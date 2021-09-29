import request from '@/utils/request'

// 查询采购订单合同关联表列表
export function listOrderContractRelation(query) {
  return request({
    url: '/business/orderContractRelation/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单合同关联表详细
export function getOrderContractRelation(id) {
  return request({
    url: '/business/orderContractRelation/' + id,
    method: 'get'
  })
}

// 新增采购订单合同关联表
export function addOrderContractRelation(data) {
  return request({
    url: '/business/orderContractRelation',
    method: 'post',
    data: data
  })
}

// 修改采购订单合同关联表
export function updateOrderContractRelation(data) {
  return request({
    url: '/business/orderContractRelation',
    method: 'put',
    data: data
  })
}

// 删除采购订单合同关联表
export function delOrderContractRelation(id) {
  return request({
    url: '/business/orderContractRelation/' + id,
    method: 'delete'
  })
}

// 导出采购订单合同关联表
export function exportOrderContractRelation(query) {
  return request({
    url: '/business/orderContractRelation/export',
    method: 'get',
    params: query
  })
}
