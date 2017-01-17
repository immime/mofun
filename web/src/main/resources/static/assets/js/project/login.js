/**
 * Created by runmain on 12/23/2016.
 */
var formvue = new Vue({
    el: "#login",
    data: {
        userName: "",
        userPwd: ""
    },
    methods: {
        login: function (e) {
            const requestData = this.$data;
            if (this.check(requestData)) {
                const user = {
                    userName: requestData.userName.trim(),
                    userPwd: md5(requestData.userPwd.trim())
                };
                this.$http.post("/login", user).then(function (response) {
                    if (response.body.success) {
                        alert(response.body.success)
                    } else {
                        alert(response.body.msg);
                    }
                }, function (response) {
                    alert(response.body.msg);
                })
            }
        },
        check: function (data) {
            if (undefined == data.userName || data.userName.trim().length == 0) {
                alert("用户名不能为空");
                return false;
            }
            if (undefined == data.userPwd || data.userPwd.trim().length == 0) {
                alert("密码不能为空");
                return false;
            }
            return true;
        }
    }

});