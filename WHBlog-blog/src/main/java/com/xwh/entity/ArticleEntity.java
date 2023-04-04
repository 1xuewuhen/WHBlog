package com.xwh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章表
 * @TableName wh_article
 */

@TableName(value ="wh_article")
@Data
@Schema(name = "ArticleEntity",title = "文章对象")
public class ArticleEntity implements Serializable {
    /**
     * 
     */
    @Schema(title = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    @Schema(title = "标题")
    private String title;

    /**
     * 文章内容
     */
    @Schema(title = "文章内容")
    private String content;

    /**
     * 文章摘要
     */
    @Schema(title = "文章摘要")
    private String summary;

    /**
     * 所属分类id
     */
    @Schema(title = "所属分类id")
    private Long categoryId;

    /**
     * 缩略图
     */
    @Schema(title = "缩略图")
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    @Schema(title = "是否置顶（0否，1是）")
    private String isTop;

    /**
     * 状态（0已发布，1草稿）
     */
    @Schema(title = "状态（0已发布，1草稿）")
    private String status;

    /**
     * 访问量
     */
    @Schema(title = "访问量")
    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */
    @Schema(title = "是否允许评论 1是，0否")
    private String isComment;

    /**
     * 
     */
    @Schema(title = "创建人")
    private Long createBy;

    /**
     * 
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    /**
     * 
     */
    @Schema(title = "更新人")
    private Long updateBy;

    /**
     * 
     */
    @Schema(title = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @Schema(title = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}