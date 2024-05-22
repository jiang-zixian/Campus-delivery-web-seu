import request from '@/utils/request'

// 查询自提订单列表
export function listZitiRecord(query) {
  return request({
    url: '/record/zitiRecord/list',
    method: 'get',
    params: query
  })
}

// 查询自提订单详细
export function getZitiRecord(recordId) {
  return request({
    url: '/record/zitiRecord/' + recordId,
    method: 'get'
  })
}

// 新增自提订单
export function addZitiRecord(data) {
  return request({
    url: '/record/zitiRecord',
    method: 'post',
    data: data
  })
}

// 修改自提订单
export function updateZitiRecord(data) {
  return request({
    url: '/record/zitiRecord',
    method: 'put',
    data: data
  })
}

// 删除自提订单
export function delZitiRecord(recordId) {
  return request({
    url: '/record/zitiRecord/' + recordId,
    method: 'delete'
  })
}
