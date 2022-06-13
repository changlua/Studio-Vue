import request from '@/utils/request'

//获取统计数
export function getCounts() {
  return request({
    url: '/index/counts',
    method: 'get'
  })
}


//获取专业、年级图表参数
export function getOptions() {
  return request({
    url: '/index/options',
    method: 'get'
  })
}


