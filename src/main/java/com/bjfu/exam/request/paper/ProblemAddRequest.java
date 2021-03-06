package com.bjfu.exam.request.paper;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProblemAddRequest {

    @NotNull(message = "试卷id不能为空!")
    private Long paperId;

    private Long fatherProblemId; //可选

    @NotBlank(message = "标题不能为空!")
    @Length(min = 1, max = 64, message = "标题长度在1-64位!")
    private String title;

    @NotBlank(message = "材料不能为空!")
    @Length(min = 1, max = 256, message = "材料长度在1-256位!")
    private String material;

    @NotNull(message = "试卷id不能为空!")
    private Integer type;

    private String answer; //可选

}
