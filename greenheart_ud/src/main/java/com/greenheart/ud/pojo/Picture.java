package com.greenheart.ud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    @TableId(value = "picture_id",type = IdType.AUTO)
    private Integer pictureId;
    private String path;
    @TableField(value="picture_type")
    private Integer pictureType;
    @TableField(value="user_id")
    private Integer userId;
    @TableField(value="information_id")
    private Integer informationId;

}
