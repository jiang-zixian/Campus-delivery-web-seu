import request from '@/utils/request'

// 查询商品列表列表
export function listItemList(query) {
  return request({
    url: '/itemList/itemList/list',
    method: 'get',
    params: query
  })
}

// 查询商品列表详细
export function getItemList(iId) {
  return request({
    url: '/itemList/itemList/' + iId,
    method: 'get'
  })
}

// 新增商品列表
export function addItemList(data) {
  return request({
    url: '/itemList/itemList',
    method: 'post',
    data: data
  })
}

// 修改商品列表
export function updateItemList(data) {
  return request({
    url: '/itemList/itemList',
    method: 'put',
    data: data
  })
}

// 删除商品列表
export function delItemList(iId) {
  return request({
    url: '/itemList/itemList/' + iId,
    method: 'delete'
  })
}

// 购买商品
export function buy(data) {
  return request({
    url: '/itemList/itemList/buy',
    method: 'post',
    data: data
  })
}
