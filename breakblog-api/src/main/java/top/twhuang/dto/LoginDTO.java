package top.twhuang.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 23:21
 * @Description: 登录DTO
 */
@Data
@ToString
public class LoginDTO {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 18, message = "密码长度6-18位")
    private String password;
}
