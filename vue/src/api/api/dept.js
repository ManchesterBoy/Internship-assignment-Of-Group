import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/deptInf',
    method: 'get',
    params: query
  })
}

export function updataDept(data) {
  return request({
    url: '/deptInf',
    method: 'put',
    data: data
  })
}

export function addDept(data) {
  return request({
    url: '/deptInf',
    method: 'post',
    data: data
  })
}

export function deleteDept(data) {
  return request({
    url: '/deptInf',
    method: 'delete',
    data: data
  })
}


