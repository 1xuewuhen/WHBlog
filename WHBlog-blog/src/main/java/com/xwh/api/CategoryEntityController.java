package com.xwh.api;

import com.xwh.entity.CategoryEntity;
import com.xwh.service.CategoryEntityService;
import com.xwh.whblogcommon.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */


@Tag(name = "Category",description = "分类模块")
@RestController
@RequestMapping("/blog/category")
public class CategoryEntityController {

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Operation(summary = "查询热点文章", description = "返回热点文章集合"
//            ,parameters = {@Parameter()}
    )
    @ApiResponse(responseCode = "2xx", description = "文章实体对象")
    @GetMapping("/getCategoryList")
    public R getCategoryList(){
        return R.ok().put(categoryEntityService.getCategoryList());
    }

}
