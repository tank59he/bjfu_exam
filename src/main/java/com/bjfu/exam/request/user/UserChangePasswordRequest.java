package com.bjfu.exam.request.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserChangePasswordRequest {

    @NotBlank(message = "账号不能为空!")
    @Length(min = 8, max = 32, message = "账号长度在8-32位!")
    private String account;

    @NotBlank(message = "原密码不能为空!")
    @Length(min = 8, max = 32, message = "密码长度在8-32位!")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空!")
    @Length(min = 8, max = 32, message = "密码长度在8-32位!")
    private String newPassword;
}
