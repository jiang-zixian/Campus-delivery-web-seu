import request from '@/utils/request'

// 查询购物车列表
export function listItemcart(query) {
  return request({
    url: '/itemcart/itemcart/list',
    method: 'get',
    params: query
  })
}

// 查询购物车详细
export function getItemcart(itemcartId) {
  return request({
    url: '/itemcart/itemcart/' + itemcartId,
    method: 'get'
  })
}

// 新增购物车
export function addItemcart(data) {
  return request({
    url: '/itemcart/itemcart',
    method: 'post',
    data: data
  })
}

// 修改购物车
export function updateItemcart(data) {
  return request({
    url: '/itemcart/itemcart',
    method: 'put',
    data: data
  })
}

// 删除购物车
export function delItemcart(itemcartId) {
  return request({
    url: '/itemcart/itemcart/' + itemcartId,
    method: 'delete'
  })
}


