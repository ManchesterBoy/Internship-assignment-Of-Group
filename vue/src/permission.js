import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken , getUser } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/auth-redirect'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  let arr = getUser() || 'null'
  if(arr != 'null'){
    store.dispatch('user/login', JSON.parse(arr))
  }


  if (Object.keys(store.getters.userInfo).length != 0) {
    //登录
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done() // hack: https://github.com/PanJiaChen/vue-element-admin/pull/2939
    } else {
      // determine whether the user has obtained his permission roles through getInfo
      const hasRoles = store.getters.roles && store.getters.roles.length > 0

      if (hasRoles) {
        next()
      } else {

        const hasToken = 'admin-token'
        const roles = []

        if(JSON.parse(arr).status == 1){
          roles.push('admin')
        } else{
          roles.push('editor')
        }
        
        next()
        const accessRoutes = await store.dispatch('permission/generateRoutes', roles)
        router.addRoutes(accessRoutes)

        // store.dispatch('user/changeRoles', 'editor')
        // 
        // resetRouter()

        // // generate accessible routes map based on roles
        // const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })
        // // dynamically add accessible routes
        // router.addRoutes(accessRoutes)

        // // reset visited views and cached views
        // dispatch('tagsView/delAllViews', null, { root: true })

       
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }



})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})