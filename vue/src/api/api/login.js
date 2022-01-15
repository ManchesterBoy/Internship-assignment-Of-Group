import request from '@/utils/request'

/**
 * 这个不要改动
 */

export async function loginUserByPass(data) {
  return request({
    url: '/userInf/loginByPass',
    method: 'post',
    data: data,
  })
}

export async function hadExitFace(data) {
  return request({
    url: '/userInf/byLoginname',
    method: 'get',
    params: data,
  })
}

export async function loginByFace(data) {
  return request({
    url: '/faceCheck',
    method: 'post',
    data: data,
  })
}
