import request from '@/utils/request'

// 查询ZfGrade列表
export function listGrade(query) {
  return request({
    url: '/team/grade/list',
    method: 'get',
    params: query
  })
}

// 查询ZfGrade详细
export function getGrade(gradeId) {
  return request({
    url: '/team/grade/' + gradeId,
    method: 'get'
  })
}

// 新增ZfGrade
export function addGrade(data) {
  return request({
    url: '/team/grade',
    method: 'post',
    data: data
  })
}

// 修改ZfGrade
export function updateGrade(data) {
  return request({
    url: '/team/grade',
    method: 'put',
    data: data
  })
}

// 删除ZfGrade
export function delGrade(gradeId) {
  return request({
    url: '/team/grade/' + gradeId,
    method: 'delete'
  })
}
