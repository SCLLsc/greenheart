package com.greenheart.pe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @TableId(value = "answer_id",type = IdType.AUTO)
    private Integer answerId;
    @TableField(value="trial_id")
    private Integer trialId;
    @TableField(value="trial_title")
    private String trialTitle;
    @TableField(value="answer_a")
    private String answerA;
    @TableField(value="answer_b")
    private String answerB;
    @TableField(value="answer_c")
    private String answerC;
    @TableField(value="answer_d")
    private String answerD;
    @TableField(value="score_a")
    private Integer scoreA;
    @TableField(value="score_b")
    private Integer scoreB;
    @TableField(value="score_c")
    private Integer scoreC;
    @TableField(value="score_d")
    private Integer scoreD;
}
