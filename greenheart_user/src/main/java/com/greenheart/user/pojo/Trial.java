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

@Alias("Trial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trial {
    @TableId(value = "trial_id",type = IdType.AUTO)
    private Integer trialId;
    @TableField(value="trial_type")
    private Integer trialType;
    @TableField(value="trial_title")
    private Integer trialTitle;
    @TableField(value="trial_content")
    private Integer trialContent;
    @TableField(value="trial_answer")
    private Integer trialAnswer;
    @TableField(value="trial_score")
    private Integer trialScore;
    private Integer cycle;
    @TableField(value="creation_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationTime;
    @TableField(value="user_id")
    private Integer userId;

}
