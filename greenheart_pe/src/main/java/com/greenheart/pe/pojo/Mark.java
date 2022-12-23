package com.greenheart.pe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Mark")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mark {
    @TableId(value = "mark_id",type = IdType.AUTO)
    private Integer markId;
    @TableField(value="trial_title")
    private String trialTitle;
    @TableField(value="mark_score")
    private Integer markScore;
    @TableField(value="mark_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date markDate;
    @TableField(value="user_id")
    private Integer userId;

}
