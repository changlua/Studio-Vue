import request from '@/utils/request'

// 查询ZfMajor列表
export function listMajor(query) {
  return request({
    url: '/team/major/list',
    method: 'get',
    params: query
  })
}

// 查询ZfMajor详细
export function getMajor(majorId) {
  return request({
    url: '/team/major/' + majorId,
    method: 'get'
  })
}

// 新增ZfMajor
export function addMajor(data) {
  return request({
    url: '/team/major',
    method: 'post',
    data: data
  })
}

// 修改ZfMajor
export function updateMajor(data) {
  return request({
    url: '/team/major',
    method: 'put',
    data: data
  })
}

// 删除ZfMajor
export function delMajor(majorId) {
  return request({
    url: '/team/major/' + majorId,
    method: 'delete'
  })
}
