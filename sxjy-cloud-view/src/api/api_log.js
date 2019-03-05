import * as API from './'

export default {
  list: params => {
    return API.POST('/api-base/errorlogs2', params)
  },
  remove: params => {
    return API.POST('/api-base/delete_errorlog', params)
  }
}
