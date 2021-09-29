import request from '@/utils/request'

// 查询商品品牌表列表
export function listBrand(query) {
  return request({
    url: '/business/brand/list',
    method: 'get',
    params: query
  })
}

// 查询商品品牌表详细
export function getBrand(id) {
  return request({
    url: '/business/brand/' + id,
    method: 'get'
  })
}

// 新增商品品牌表
export function addBrand(data) {
  return request({
    url: '/business/brand',
    method: 'post',
    data: data
  })
}

// 修改商品品牌表
export function updateBrand(data) {
  return request({
    url: '/business/brand',
    method: 'put',
    data: data
  })
}

// 删除商品品牌表
export function delBrand(id) {
  return request({
    url: '/business/brand/' + id,
    method: 'delete'
  })
}

// 导出商品品牌表
export function exportBrand(query) {
  return request({
    url: '/business/brand/export',
    method: 'get',
    params: query
  })
}
