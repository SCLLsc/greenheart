package com.greenheart.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Collect")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect {
    @TableId(value = "collect_id",type = IdType.AUTO)
    private Integer collectId;
    @TableField(value="information_id")
    private Integer informationId;
    @TableField(value="user_id")
    private Integer userId;
}
