import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function setUser(data) {
  return Cookies.set('userInfo', data)
}

export function getUser(data) {
  return Cookies.get('userInfo')
}

export function removeUser() {
  return Cookies.remove('userInfo')
}

export function getAccount() {
  return localStorage.getItem('account')
}

export function setAccount(data) {
  return localStorage.setItem("account",JSON.stringify(data)) 
}

export function removeAccount() {
  return localStorage.removeItem('account')
}