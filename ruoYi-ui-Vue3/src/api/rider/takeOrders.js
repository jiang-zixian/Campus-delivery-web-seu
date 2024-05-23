import request from '@/utils/request'

// 查询我要接单列表
export function listTakeOrders(query) {
  return request({
    url: '/rider/takeOrders/list',
    method: 'get',
    params: query
  })
}

// 查询我要接单详细
export function getTakeOrders(recordId) {
  return request({
    url: '/rider/takeOrders/' + recordId,
    method: 'get'
  })
}

// 新增我要接单
export function addTakeOrders(data) {
  return request({
    url: '/rider/takeOrders',
    method: 'post',
    data: data
  })
}

// 修改我要接单
export function updateTakeOrders(data) {
  return request({
    url: '/rider/takeOrders',
    method: 'put',
    data: data
  })
}

// 删除我要接单
export function delTakeOrders(recordId) {
  return request({
    url: '/rider/takeOrders/' + recordId,
    method: 'delete'
  })
}
