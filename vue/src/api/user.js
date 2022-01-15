import request from '@/utils/request'

export async function login(data) {
  return request({
    url: '/api/userInf/loginByPass',
    method: 'post',
    data: data,
  })
}


export function getInfo(token) {
  return request({
    url: '/vue-element-admin/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}




export function testTomcat() {
  return request({
    url: '/api/queryAllDepartment',
    method: 'get'
  })
}

