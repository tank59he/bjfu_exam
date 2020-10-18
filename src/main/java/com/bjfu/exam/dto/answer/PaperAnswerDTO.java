package com.bjfu.exam.dto.answer;

import lombok.Data;


@Data
public class PaperAnswerDTO {
    /**
     * 答卷id
     */
    private Long id;
    /**
     * 收集项答案(JSON)
     */
    private String collectionAnswer;
    /**
     * 答卷状态
     */
    private Integer state;
}