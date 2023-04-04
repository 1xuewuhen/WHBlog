package com.xwh.whblogcommon.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ArticleEntityVo implements Serializable {

    private Long id;
    private String title;
    private Long viewCount;
    @Serial
    private static final long serialVersionUID = 1L;
}
