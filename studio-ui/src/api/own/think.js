import request from '@/utils/request'

// 查询ZfThink列表
export function listThink(query) {
  return request({
    url: '/own/think/list',
    method: 'get',
    params: query
  })
}

// 查询ZfThink详细
export function getThink(thinkId) {
  return request({
    url: '/own/think/' + thinkId,
    method: 'get'
  })
}

// 新增ZfThink
export function addThink(data) {
  return request({
    url: '/own/think',
    method: 'post',
    data: data
  })
}

// 修改ZfThink
export function updateThink(data) {
  return request({
    url: '/own/think',
    method: 'put',
    data: data
  })
}

// 删除ZfThink
export function delThink(thinkId) {
  return request({
    url: '/own/think/' + thinkId,
    method: 'delete'
  })
}
