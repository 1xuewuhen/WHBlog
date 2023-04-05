package com.xwh.whblogcommon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List<?> rows;
    private Long total;
}
