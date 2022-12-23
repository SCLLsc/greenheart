package com.greenheart.pim.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    @TableField(value="user_name")
    private String userName;
    @TableField(value="user_pwd")
    private String userPwd;
    private String email;
    private Integer role;
}
