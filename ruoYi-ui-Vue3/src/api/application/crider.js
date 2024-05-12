import request from '@/utils/request'

// 查询所有骑手申请列表
export function listCrider(query) {
  return request({
    url: '/application/crider/Allist',
    method: 'get',
    params: query
  })
}

// 查询当前用户的骑手申请列表
export function listCriderByUser(query) {
  return request({
    url: '/application/crider/listByUser',
    method: 'get',
    params: query
  })
}


// 查询骑手申请详细
export function getCrider(criderId) {
  return request({
    url: '/application/crider/' + criderId,
    method: 'get'
  })
}

// 新增骑手申请
export function addCrider(data) {
  return request({
    url: '/application/crider',
    method: 'post',
    data: data
  })
}

// 修改骑手申请
export function updateCrider(data) {
  return request({
    url: '/application/crider',
    method: 'put',
    data: data
  })
}

// 删除骑手申请
export function delCrider(criderId) {
  return request({
    url: '/application/crider/' + criderId,
    method: 'delete'
  })
}

// 同意骑手申请
export function agreeCrider(criderId) {
  return request({
    url: '/application/crider/agree/'+ criderId,
    method: 'delete',
  })
}

//拒绝骑手申请
export function refuseCrider(criderId) {
  return request({
    url: '/application/crider/refuse/'+ criderId,
    method: 'delete',
  })
}



