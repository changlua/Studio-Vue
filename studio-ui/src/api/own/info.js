import request from '@/utils/request'

//获取菜单数据：专业、年级
const getMenu = function(query) {
  return request({
    url: '/own/info/getMenu',
    method: 'get'
  })
}

//获取用户信息接口
const getUserInfo = function(query) {
  return request({
    url: '/own/info',
    method: 'get'
  })
}

//信息录入
const commitUserInfo = function(data) {
  return request({
    url: '/own/info',
    method: 'post',
    data: data
  })
}

export default { getMenu,getUserInfo,commitUserInfo }
