package com.greenheart.naam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
   @TableId(value = "notice_id",type = IdType.AUTO)
   private Integer noticeId;
   @TableField(value="notice_title")
   private String noticeTitle;
   @TableField(value="notice_content")
   private String noticeContent;
   @TableField(value="creation_time")
   @JsonFormat(pattern = "yyyy-MM-dd")
   private Date creationTime;
   @TableField(value="update_time")
   @JsonFormat(pattern = "yyyy-MM-dd")
   private Date updateTime;

}
