import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/employeeInf',
    method: 'get',
    params: query
  })
}

export function updateEmp(data) {
  return request({
    url: '/employeeInf',
    method: 'put',
    data: data
  })
}

export function addEmp(data) {
  return request({
    url: '/employeeInf',
    method: 'post',
    data: data
  })
}

export function deleteEmp(data) {
  return request({
    url: '/employeeInf',
    method: 'delete',
    data: data
  })
}


