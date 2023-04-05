package com.xwh.whblogcommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */

@Data
public class LinkVo {

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String logo;

    /**
     *
     */
    private String description;

    /**
     * 网站地址
     */
    private String address;

}
