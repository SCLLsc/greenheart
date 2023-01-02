package com.greenheart.um.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    @TableId(value = "reply_id",type = IdType.AUTO)
    private Integer replyId;
    @TableField(value="reply_content")
    private String replyContent;
    @TableField(value="reply_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date replyDate;
    @TableField(value="guidance_id")
    private Integer guidanceId;

}
