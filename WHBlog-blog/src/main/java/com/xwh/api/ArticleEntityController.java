package com.xwh.api;


import com.xwh.service.ArticleEntityService;
import com.xwh.whblogcommon.utils.R;
import com.xwh.whblogcommon.vo.ArticleDetailVo;
import com.xwh.whblogcommon.vo.ArticleEntityVo;
import com.xwh.whblogcommon.vo.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "article 模块", description = "article API")
@RestController
@RequestMapping("/blog/article")
public class ArticleEntityController {

    @Autowired
    private ArticleEntityService articleEntityService;

    @Operation(summary = "查询热点文章", description = "返回热点文章集合"
//            ,parameters = {@Parameter()}
    )
    @ApiResponse(responseCode = "2xx", description = "文章实体对象")
    @GetMapping("/hotArticleList")
    public R hotArticleList() {
        List<ArticleEntityVo> data = articleEntityService.hotArticleList();
        return R.ok().put(data);
    }

    @Operation(summary = "分页查询文章列表", description = "返回文章集合"
//            ,parameters = {@Parameter()}
    )
    @ApiResponse(responseCode = "2xx", description = "文章实体对象")
    @GetMapping("/articleList")
    public R articleList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam(value = "categoryId", defaultValue = "-1") Long categoryId) {
        PageVo pageVo = articleEntityService.articleList(pageNum, pageSize, categoryId);
        return R.ok().put(pageVo);
    }

    @Operation(summary = "查询文章详情", description = "返回文章详情"
            , parameters = {@Parameter(name = "id", description = "文章id")}
    )
    @ApiResponse(responseCode = "2xx", description = "文章详情对象")
    @GetMapping("/{id}")
    public R getArticleDetail(@PathVariable("id") Long id) {
        ArticleDetailVo articleDetail = articleEntityService.getArticleDetail(id);
        return R.ok().put(articleDetail);
    }

}
