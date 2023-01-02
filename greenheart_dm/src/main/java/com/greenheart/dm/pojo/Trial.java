package com.greenheart.dm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Trial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trial {
    @TableId(value = "trial_id",type = IdType.AUTO)
    private Integer trialId;
    @TableField(value="trial_type")
    private String trialType;
    @TableField(value="trial_title")
    private String trialTitle;
    @TableField(value="trial_content")
    private String trialContent;
    @TableField(value="trial_answer")
    private String trialAnswer;
    @TableField(value="trial_score")
    private Integer trialScore;
    private Integer cycle;
    @TableField(value="creation_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date creationTime;
    @TableField(value="user_id")
    private Integer userId;

}
