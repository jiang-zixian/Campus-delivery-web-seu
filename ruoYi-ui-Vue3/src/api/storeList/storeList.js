import request from '@/utils/request'

// 查询商家列表列表
export function listStoreList(query) {
  return request({
    url: '/storeList/storeList/list',
    method: 'get',
    params: query
  })
}

// 查询商家列表详细
export function getStoreList(sId) {
  return request({
    url: '/storeList/storeList/' + sId,
    method: 'get'
  })
}

// 新增商家列表
export function addStoreList(data) {
  return request({
    url: '/storeList/storeList',
    method: 'post',
    data: data
  })
}

// 修改商家列表
export function updateStoreList(data) {
  return request({
    url: '/storeList/storeList',
    method: 'put',
    data: data
  })
}

// 删除商家列表
export function delStoreList(sId) {
  return request({
    url: '/storeList/storeList/' + sId,
    method: 'delete'
  })
}
