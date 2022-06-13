import request from '@/utils/request'

// 查询ZfRace列表
export function listRace(query) {
  return request({
    url: '/own/race/list',
    method: 'get',
    params: query
  })
}

// 查询ZfRace详细
export function getRace(raceId) {
  return request({
    url: '/own/race/' + raceId,
    method: 'get'
  })
}

// 查询指定竞赛的成员信息
export function getRaceMembers(raceId) {
  return request({
    url: '/own/race/members/' + raceId,
    method: 'get'
  })
}

// 新增ZfRace
export function addRace(data) {
  return request({
    url: '/own/race',
    method: 'post',
    data: data
  })
}

// 修改ZfRace
export function updateRace(data) {
  return request({
    url: '/own/race',
    method: 'put',
    data: data
  })
}

// 删除ZfRace
export function delRace(raceId) {
  return request({
    url: '/own/race/' + raceId,
    method: 'delete'
  })
}
