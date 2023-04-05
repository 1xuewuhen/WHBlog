package com.xwh.whblogcommon.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */

@Data
public class ArticleDetailVo {


    private Long id;

    private String title;

    private String summary;

    private String categoryName;

    private String content;

    private Long categoryId;

    private String thumbnail;

    private Long viewCount;

    private LocalDateTime createTime;
}
