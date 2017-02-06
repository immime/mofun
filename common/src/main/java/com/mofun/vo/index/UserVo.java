package com.mofun.vo.index;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by runmain on 2017/1/22.
 */
public class UserVo implements Serializable {
    private static final long serialVersionUID = 8263059811738393258L;
    @NotBlank(message = "名称为空", groups = {Reg.class})
    private String userName;
    @Pattern(regexp = "([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+\\.([a-zA-Z0-9_-])+", message = "邮箱不合法", groups = {Reg.class, Login.class, Reset.class})
    private String email;
    @NotBlank(message = "密码不能为空", groups = {Reg.class, Login.class})
    @Length(min = 6, max = 15, message = "密码长度6-15", groups = {Reg.class})
    private String password;
    @NotBlank(message = "确认密码不能为空", groups = {Reg.class})
    @Length(min = 6, max = 15, message = "确认密码长度6-15", groups = {Reg.class})
    private String pwdConfirm;
    @NotBlank(message = "验证码不能为空", groups = {Reg.class})
    private String imageCode;
    @NotBlank(message = "手机号不能为空",groups = Reg.class)
    @Pattern(regexp = "1\\d{10}",message = "请检查手机号是否正确",groups = Reg.class)
    @Length(min = 11,max = 11,message = "",groups = Reg.class)
    private String mobilePhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public interface Login {
    }

    public interface Reg {
    }

    public interface Reset {
    }
}
