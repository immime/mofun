/**
 * Created by runmain on 12/23/2016.
 */
var formvue = new Vue({
    el: "#login",
    data: {
        user: {
            password: "",
            email: ""
        }
    },
    methods: {
        login: function (e) {
            e.preventDefault();
            if (this.check(this.user)) {
                this.user.password = md5(this.user.password);
                this.$http.post("/login", this.user).then(function (response) {
                    if (response.body.success) {
                        this.showMessage("success");
                    } else {
                        this.showMessage(response.body.msg);
                    }
                }, function (response) {
                    this.showMessage(response.body.msg);
                })
            }
        },
        check: function (data) {
            if (undefined == data.email || data.email.trim().length == 0) {
                this.showMessage("用户名不能为空");
                return false;
            }
            if (undefined == data.password || data.password.trim().length == 0) {
                this.showMessage("密码不能为空");
                return false;
            }
            return true;
        },
        showMessage: function (msg) {
            alert(msg);
        }
    }

});