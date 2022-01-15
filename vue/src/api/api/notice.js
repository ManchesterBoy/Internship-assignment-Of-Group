import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/noticeInf',
    method: 'get',
    params: query
  })
}

export function updataNotice(data) {
  return request({
    url: '/noticeInf',
    method: 'put',
    data: data
  })
}

export function addNotice(data) {
  return request({
    url: '/noticeInf',
    method: 'post',
    data: data
  })
}

export function deleteNotice(data) {
  return request({
    url: '/noticeInf',
    method: 'delete',
    data: data
  })
}


