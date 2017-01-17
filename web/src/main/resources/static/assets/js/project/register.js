/**
 * Created by runmain on 1/12/2017.
 */
var reg = new Vue({
    el: "#register",
    data: {
        userName: "",
        email: "",
        password: "",
        password2: "",
        agree: false
    },
    methods: {
        checkInit: function (e) {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            }).on("ifChecked", function (e) {
                reg.$data.agree = true;
            }).on("ifUnchecked", function () {
                reg.$data.agree = false;
            });
        },
        register: function (e) {
            e.preventDefault();
        }
    }

});
reg.checkInit();