import request from '@/utils/request'

// 查询User列表
export function listMember(query) {
  return request({
    url: '/team/member/list',
    method: 'get',
    params: query
  })
}

// 查询User详细
export function getMember(userId) {
  return request({
    url: '/team/member/' + userId,
    method: 'get'
  })
}

// 修改User
export function updateMember(data) {
  return request({
    url: '/team/member',
    method: 'put',
    data: data
  })
}

//创建账号
export function addMember(data){
  return request({
    url: '/team/member',
    method: 'post',
    data: data
  })
}

//注销账号
export function cancelledMember(userId) {
  return request({
    url: '/team/member/cancelled/' + userId,
    method: 'delete'
  })
}

//激活账号
export function activeMember(userId) {
  return request({
    url: '/team/member/active/' + userId,
    method: 'put'
  })
}

//转让负责人
export function transfer(userId) {
  return request({
    url: '/team/member/transfer/' + userId,
    method: 'put'
  })
}

//重置密码
export function resetPwd(userId) {
  return request({
    url: '/team/member/resetpwd/' + userId,
    method: 'put'
  })
}

// 删除账号
export function delMember(userId) {
  return request({
    url: '/team/member/' + userId,
    method: 'delete'
  })
}
