package com.xwh.api;


import com.xwh.service.ArticleEntityService;
import com.xwh.whblogcommon.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "article 模块", description = "article API")
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleEntityService articleEntityService;

    @Operation(summary = "查询热点文章", description = "返回热点文章集合"
//            ,parameters = {@Parameter()}
    )
    @ApiResponse(responseCode = "2xx",description = "文章实体对象")
    @GetMapping("/hotArticleList")
    public R hotArticleList() {
        return R.ok().put(articleEntityService.hotArticleList());
    }
}
