import request from '@/utils/request'

// 查询ZfRace列表
export function listRace(query) {
  return request({
    url: '/team/race/list',
    method: 'get',
    params: query
  })
}

// 查询所有用户id与姓名
export function getMemberOptions() {
  return request({
    url: '/team/race/memberoptions',
    method: 'get'
  })
}

// 查询ZfRace详细
export function getRace(raceId) {
  return request({
    url: '/team/race/' + raceId,
    method: 'get'
  })
}

// 新增ZfRace
export function addRace(data) {
  return request({
    url: '/team/race',
    method: 'post',
    data: data
  })
}

// 修改ZfRace
export function updateRace(data) {
  return request({
    url: '/team/race',
    method: 'put',
    data: data
  })
}

// 删除ZfRace
export function delRace(raceId) {
  return request({
    url: '/team/race/' + raceId,
    method: 'delete'
  })
}
