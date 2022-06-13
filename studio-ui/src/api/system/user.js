import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询用户个人信息
const getUserProfile =  function() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
const updateUserProfile = function(data){
  return request({
    url: '/system/user/profile',
    method: 'post',
    data: data
  })
}

// 用户密码重置
const updateUserPwd = function(oldPassword, newPassword){
  const data = {
    oldPassword,
    password: newPassword
  }
  return request({
    url: '/system/user/updatePwd',
    method: 'post',
    data: data
  })
}

// 用户头像上传
const uploadAvatar = function(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

export default {getUserProfile, updateUserProfile, updateUserPwd, uploadAvatar}

// // 用户密码重置
// export function resetUserPwd(userId, password) {
//   const data = {
//     userId,
//     password
//   }
//   return request({
//     url: '/system/user/resetPwd',
//     method: 'put',
//     data: data
//   })
// }

// // 修改用户
// export function updateUser(data) {
//   return request({
//     url: '/system/user',
//     method: 'put',
//     data: data
//   })
// }

// // 查询用户列表
// export function listUser(query) {
//   return request({
//     url: '/system/user/list',
//     method: 'get',
//     params: query
//   })
// }
//
// // 查询用户详细
// export function getUser(userId) {
//   return request({
//     url: '/system/user/' + parseStrEmpty(userId),
//     method: 'get'
//   })
// }
//
// // 新增用户
// export function addUser(data) {
//   return request({
//     url: '/system/user',
//     method: 'post',
//     data: data
//   })
// }
//
// // 修改用户
// export function updateUser(data) {
//   return request({
//     url: '/system/user',
//     method: 'put',
//     data: data
//   })
// }
//
// // 删除用户
// export function delUser(userId) {
//   return request({
//     url: '/system/user/' + userId,
//     method: 'delete'
//   })
// }
//
// // 用户密码重置
// export function resetUserPwd(userId, password) {
//   const data = {
//     userId,
//     password
//   }
//   return request({
//     url: '/system/user/resetPwd',
//     method: 'put',
//     data: data
//   })
// }
//
// // 用户状态修改
// export function changeUserStatus(userId, status) {
//   const data = {
//     userId,
//     status
//   }
//   return request({
//     url: '/system/user/changeStatus',
//     method: 'put',
//     data: data
//   })
// }
//
//
//

//

//

//
// // 查询授权角色
// export function getAuthRole(userId) {
//   return request({
//     url: '/system/user/authRole/' + userId,
//     method: 'get'
//   })
// }
//
// // 保存授权角色
// export function updateAuthRole(data) {
//   return request({
//     url: '/system/user/authRole',
//     method: 'put',
//     params: data
//   })
// }
