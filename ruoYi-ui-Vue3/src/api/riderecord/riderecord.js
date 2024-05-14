import request from '@/utils/request'

// 查询跑腿列表
export function listRiderecord(query) {
  return request({
    url: '/riderecord/riderecord/list',
    method: 'get',
    params: query
  })
}

// 查询跑腿详细
export function getRiderecord(recordId) {
  return request({
    url: '/riderecord/riderecord/' + recordId,
    method: 'get'
  })
}

// 新增跑腿
export function addRiderecord(data) {
  return request({
    url: '/riderecord/riderecord',
    method: 'post',
    data: data
  })
}

// 修改跑腿
export function updateRiderecord(data) {
  return request({
    url: '/riderecord/riderecord',
    method: 'put',
    data: data
  })
}

// 删除跑腿
export function delRiderecord(recordId) {
  return request({
    url: '/riderecord/riderecord/' + recordId,
    method: 'delete'
  })
}
