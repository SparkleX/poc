Vue.component('todo-item', {
  template: '<li>这是个待办项</li>'
});
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})
var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于 ' + new Date().toLocaleString()
    }
})
var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
})

var app4 = new Vue({
    el: '#app-4',
    data: {
        message: 'Hello Vue.js!'
    },
    methods: {
        onClick: function () {
            this.message = this.message.split('').reverse().join('')
        }
    }
}) 


