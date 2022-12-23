package com.greenheart.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Guidance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guidance {
    @TableId(value = "guidance_id",type = IdType.AUTO)
    private Integer guidanceId;
    @TableField(value="guidance_content")
    private String guidanceContent;
    @TableField(value="guidance_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date guidanceDate;
    @TableField(value="guidance_status")
    private Integer guidanceStatus;
    @TableField(value="user_id")
    private Integer userId;

}
