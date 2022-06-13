import request from '@/utils/request'

// 查询ZfCcie列表
export function listCcie(query) {
  return request({
    url: '/own/ccie/list',
    method: 'get',
    params: query
  })
}

// 查询ZfCcie详细
export function getCcie(ccieId) {
  return request({
    url: '/own/ccie/' + ccieId,
    method: 'get'
  })
}

// 新增ZfCcie
export function addCcie(data) {
  return request({
    url: '/own/ccie',
    method: 'post',
    data: data
  })
}

// 修改ZfCcie
export function updateCcie(data) {
  return request({
    url: '/own/ccie',
    method: 'put',
    data: data
  })
}

// 删除ZfCcie
export function delCcie(ccieId) {
  return request({
    url: '/own/ccie/' + ccieId,
    method: 'delete'
  })
}
