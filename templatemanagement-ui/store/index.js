export const state = () => ({
  sidebar: false,
  messages: [],
  snackbar: false
})

export const mutations = {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  },
  addMessage (state, message) {
    message.timestamp = new Date().getTime()
    state.messages.push(message)
    state.snackbar = true
  },
  removeMessage (state, message) {
    state.messages.splice(state.messages.indexOf(message), 1)
  },
  disableSnackbar (state) {
    state.snackbar = false
  }
}
