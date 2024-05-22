import request from '@/utils/request'

// 查询我的跑腿订单列表
export function listMyriderecord(query) {
  return request({
    url: '/record/myriderecord/list',
    method: 'get',
    params: query
  })
}

// 查询我的跑腿订单详细
export function getMyriderecord(recordId) {
  return request({
    url: '/record/myriderecord/' + recordId,
    method: 'get'
  })
}

// 新增我的跑腿订单
export function addMyriderecord(data) {
  return request({
    url: '/record/myriderecord',
    method: 'post',
    data: data
  })
}

// 修改我的跑腿订单
export function updateMyriderecord(data) {
  return request({
    url: '/record/myriderecord',
    method: 'put',
    data: data
  })
}

// 删除我的跑腿订单
export function delMyriderecord(recordId) {
  return request({
    url: '/record/myriderecord/' + recordId,
    method: 'delete'
  })
}
