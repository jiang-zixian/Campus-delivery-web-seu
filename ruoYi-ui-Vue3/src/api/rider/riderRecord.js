import request from '@/utils/request'

// 查询历史接单记录列表
export function listRiderRecord(query) {
  return request({
    url: '/rider/riderRecord/list',
    method: 'get',
    params: query
  })
}

// 查询历史接单记录详细
export function getRiderRecord(recordId) {
  return request({
    url: '/rider/riderRecord/' + recordId,
    method: 'get'
  })
}

// 新增历史接单记录
export function addRiderRecord(data) {
  return request({
    url: '/rider/riderRecord',
    method: 'post',
    data: data
  })
}

// 修改历史接单记录
export function updateRiderRecord(data) {
  return request({
    url: '/rider/riderRecord',
    method: 'put',
    data: data
  })
}

// 删除历史接单记录
export function delRiderRecord(recordId) {
  return request({
    url: '/rider/riderRecord/' + recordId,
    method: 'delete'
  })
}
