import request from '@/utils/request'

// 查询商家提交申请列表
export function listCstore(query) {
  return request({
    url: '/application/cstore/list',
    method: 'get',
    params: query
  })
}

// 查询商家提交申请详细
export function getCstore(cstoreId) {
  return request({
    url: '/application/cstore/' + cstoreId,
    method: 'get'
  })
}

// 新增商家提交申请
export function addCstore(data) {
  return request({
    url: '/application/cstore',
    method: 'post',
    data: data
  })
}

// 修改商家提交申请
export function updateCstore(data) {
  return request({
    url: '/application/cstore',
    method: 'put',
    data: data
  })
}

// 删除商家提交申请
export function delCstore(cstoreId) {
  return request({
    url: '/application/cstore/' + cstoreId,
    method: 'delete'
  })
}

export function agreeCstore(cstoreId) {
  return request({
    url: '/application/cstore/agree/'+ cstoreId,
    method: 'delete',
  })
}

export function refuseCstore(cstoreId) {
  return request({
    url: '/application/cstore/refuse/'+ cstoreId,
    method: 'delete',
  })
}
