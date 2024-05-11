import request from '@/utils/request'

// 查询我的订单列表
export function listRecord(query) {
  return request({
    url: '/record/record/list',
    method: 'get',
    params: query
  })
}

// 查询我的订单详细
export function getRecord(recordId) {
  return request({
    url: '/record/record/' + recordId,
    method: 'get'
  })
}

// 新增我的订单
export function addRecord(data) {
  return request({
    url: '/record/record',
    method: 'post',
    data: data
  })
}

// 修改我的订单
export function updateRecord(data) {
  return request({
    url: '/record/record',
    method: 'put',
    data: data
  })
}

// 删除我的订单
export function delRecord(recordId) {
  return request({
    url: '/record/record/' + recordId,
    method: 'delete'
  })
}
