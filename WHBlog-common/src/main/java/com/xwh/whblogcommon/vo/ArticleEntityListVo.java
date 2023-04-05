package com.xwh.whblogcommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */
@Data
public class ArticleEntityListVo {

    /**
     *
     */
    private Long id;

    /**
     * 标题
     */
    private String title;
    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 所属分类id
     */
    private String categoryName;
    private Long categoryId;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 访问量
     */
    private Long viewCount;


    /**
     *
     */
    private LocalDateTime createTime;
}
