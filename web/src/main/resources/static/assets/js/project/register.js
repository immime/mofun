/**
 * Created by runmain on 1/12/2017.
 */
var register = new Vue({
    el: "#register",
    data: {
        user: {
            userName: "",
            email: "",
            password: "",
            pwdConfirm: "",
            imageCode: "",
            mobilePhone: "",
            agree: false
        },
        authUrl: "/auth/imageCode?cc=" + Math.random().toString(12).substr(2),
    },
    methods: {
        checkInit: function (e) {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            }).on("ifChecked", function (e) {
                register.$data.user.agree = true;
            }).on("ifUnchecked", function () {
                register.$data.user.agree = false;
            });
        },
        register: function (e) {
            e.preventDefault();
            if (this.validUser()) {
                // this.user.password = md5(this.user.password.trim());
                // this.user.pwdConfirm = md5(this.user.pwdConfirm.trim());
                this.$http.post("/reg", this.user).then(function (res) {
                    if (!res.body.success) {
                        this.showMessage(res.body.msg);
                        this.freshCode();
                    } else {
                        this.showMessage("注册成功,请登录注册邮箱激活该账户后使用");
                        window.location.href = "/";
                    }
                }, function (res) {
                    this.showMessage(res.body.msg);
                    this.freshCode();
                });
            }
        },
        freshCode: function () {
            this.authUrl = "/auth/imageCode?cc=" + Math.random().toString(12).substr(2);
        },
        validUser: function () {
            if (this.user.userName.trim() == '') {
                this.showMessage("请检查名称是否填写正确");
                return false;
            }
            if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(this.user.email)) {
                this.showMessage("请检查邮箱是否填写正确");
                return false;
            }
            if ((!(this.user.password.trim().length >= 6 && this.user.password.trim().length < 15 && this.user.password == this.user.pwdConfirm))) {
                this.showMessage("请检查密码是否填写正确");
                return false;
            }
            if (this.user.imageCode.trim() == '') {
                this.showMessage("验证码不能为空");
                return false;
            }
            if (!/^1\d{10}$/.test(this.user.mobilePhone)) {
                this.showMessage("请正确输入手机号");
                return false;
            }
            return true;
        },
        showMessage: function (msg) {
            alert(msg);
        }
    }

});
register.checkInit();