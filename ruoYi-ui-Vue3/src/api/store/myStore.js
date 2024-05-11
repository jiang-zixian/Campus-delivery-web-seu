import request from '@/utils/request'

// 查询我的商店列表
export function listMyStore(query) {
  return request({
    url: '/store/myStore/list',
    method: 'get',
    params: query
  })
}

// 查询我的商店详细
export function getMyStore(sId) {
  return request({
    url: '/store/myStore/' + sId,
    method: 'get'
  })
}

// 新增我的商店
export function addMyStore(data) {
  return request({
    url: '/store/myStore',
    method: 'post',
    data: data
  })
}

// 修改我的商店
export function updateMyStore(data) {
  return request({
    url: '/store/myStore',
    method: 'put',
    data: data
  })
}

// 删除我的商店
export function delMyStore(sId) {
  return request({
    url: '/store/myStore/' + sId,
    method: 'delete'
  })
}
