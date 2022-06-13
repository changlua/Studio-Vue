import request from '@/utils/request'

// 查询ZfCcie列表
export function listCcie(query) {
  return request({
    url: '/team/ccie/list',
    method: 'get',
    params: query
  })
}

