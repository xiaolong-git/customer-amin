import request from '@/utils/request'

// 查询商品表列表
export function listProduct(query) {
  return request({
    url: '/business/product/list',
    method: 'get',
    params: query
  })
}

// 根据商品属性查询商品表列表
export function listProductByProperty(query) {
  return request({
    url: '/business/product/listByProperty',
    method: 'get',
    params: query
  })
}

// 查询商品表详细
export function getProduct(id) {
  return request({
    url: '/business/product/' + id,
    method: 'get'
  })
}

// 新增商品表
export function addProduct(data) {
  return request({
    url: '/business/product',
    method: 'post',
    data: data
  })
}

// 修改商品表
export function updateProduct(data) {
  return request({
    url: '/business/product',
    method: 'put',
    data: data
  })
}

// 删除商品表
export function delProduct(id) {
  return request({
    url: '/business/product/' + id,
    method: 'delete'
  })
}

// 导出商品表
export function exportProduct(query) {
  return request({
    url: '/business/product/export',
    method: 'get',
    params: query
  })
}
