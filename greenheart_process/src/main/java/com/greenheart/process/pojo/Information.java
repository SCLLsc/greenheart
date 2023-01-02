package com.greenheart.process.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    @TableId(value = "information_id",type = IdType.AUTO)
    private Integer informationId;
    @TableField(value="information_type")
    private String informationType;
    @TableField(value="information_title")
    private String informationTitle;
    @TableField(value="information_content")
    private String informationContent;
    @TableField(value="information_status")
    private Integer informationStatus;
    @TableField(value="upload_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date uploadTime;
    @TableField(value="user_id")
    private Integer userId;
}
