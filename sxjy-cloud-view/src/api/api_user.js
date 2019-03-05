/**
 * Created by bootdo.
 * 用户相关api
 */
import * as API from './'

export default {
  //登录
  login: params => {
    return API.POST('/api-auth/token', params)
  },
  //登出
  logout: params => {
    return API.GET('/api-admin/logout', params)
  },
  menus: params => {
    return API.POST('/api-authorize/menus', params)
  },
  //修改个人信息
  changeProfile: params => {
    return API.PATCH('/api/v1/users/profile', params)
  },

  //查询获取user列表(通过page分页)
  findList: params => {
    return API.GET('/api-admin/user', params)
  },

  //增加用户
  addUser:params =>{
    return API.POST('/api-admin/user',params)
  },
  //修改用户
  editUser:params =>{
    return API.PUT('/api-admin/user',params)
  },
  //删除用户
  removeUser:params =>{
    return API.DELETE('/api-admin/user',params)
  }
}
