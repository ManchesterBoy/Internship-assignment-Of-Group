import request from '@/utils/request'

/**
 * 文件接口
 */

export async function upload(data) {
  return request({
    url: '/upload',
    method: 'post',
    data,
    headers:{
      'Content-Type':'multipart/form-data'
    }
  })
}


export function fetchList(query) {
  return request({
    url: '/documentInf',
    method: 'get',
    params: query
  })
}

export function updataDocu(data) {
  return request({
    url: '/documentInf',
    method: 'put',
    data: data
  })
}

export function addDocu(data) {
  return request({
    url: '/documentInf',
    method: 'post',
    data: data
  })
}

export function deleteDocu(data) {
  return request({
    url: '/documentInf',
    method: 'delete',
    data: data
  })
}



export function faceUser(data) {
  return request({
    url: '/uploadBase64',
    method: 'post',
    data: data
  })
}