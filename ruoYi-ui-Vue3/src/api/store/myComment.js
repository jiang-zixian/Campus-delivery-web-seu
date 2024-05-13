import request from '@/utils/request'

// 查询我的评论列表
export function listMyComment(query) {
  return request({
    url: '/store/myComment/list',
    method: 'get',
    params: query
  })
}

// 查询我的评论详细
export function getMyComment(commentId) {
  return request({
    url: '/store/myComment/' + commentId,
    method: 'get'
  })
}

// 新增我的评论
export function addMyComment(data) {
  return request({
    url: '/store/myComment',
    method: 'post',
    data: data
  })
}

// 修改我的评论
export function updateMyComment(data) {
  return request({
    url: '/store/myComment',
    method: 'put',
    data: data
  })
}

// 删除我的评论
export function delMyComment(commentId) {
  return request({
    url: '/store/myComment/' + commentId,
    method: 'delete'
  })
}
