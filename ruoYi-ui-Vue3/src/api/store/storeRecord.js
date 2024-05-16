import request from '@/utils/request'

// 查询商店订单记录列表
export function listStoreRecord(query) {
  return request({
    url: '/store/storeRecord/list',
    method: 'get',
    params: query
  })
}

// 查询商店订单记录详细
export function getStoreRecord(recordId) {
  return request({
    url: '/store/storeRecord/' + recordId,
    method: 'get'
  })
}

// 新增商店订单记录
export function addStoreRecord(data) {
  return request({
    url: '/store/storeRecord',
    method: 'post',
    data: data
  })
}

// 修改商店订单记录
export function updateStoreRecord(data) {
  return request({
    url: '/store/storeRecord',
    method: 'put',
    data: data
  })
}

// 删除商店订单记录
export function delStoreRecord(recordId) {
  return request({
    url: '/store/storeRecord/' + recordId,
    method: 'delete'
  })
}
