import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/jobInf',
    method: 'get',
    params: query
  })
}

export function updataJob(data) {
  return request({
    url: '/jobInf',
    method: 'put',
    data: data
  })
}

export function addJob(data) {
  return request({
    url: '/jobInf',
    method: 'post',
    data: data
  })
}

export function deleteJob(data) {
  return request({
    url: '/jobInf',
    method: 'delete',
    data: data
  })
}


