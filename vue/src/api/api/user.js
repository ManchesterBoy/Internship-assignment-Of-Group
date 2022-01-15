import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/userInf',
    method: 'get',
    params: query
  })
}

export function updataUser(data) {
  return request({
    url: '/userInf',
    method: 'put',
    data: data
  })
}

export function addUser(data) {
  return request({
    url: '/userInf',
    method: 'post',
    data: data
  })
}

export function deleteUser(data) {
  return request({
    url: '/userInf',
    method: 'delete',
    data: data
  })
}


