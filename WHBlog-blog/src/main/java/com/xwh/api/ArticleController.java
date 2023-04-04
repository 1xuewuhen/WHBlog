package com.xwh.api;


import com.xwh.entity.ArticleEntity;
import com.xwh.service.ArticleEntityService;
import com.xwh.whblogcommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleEntityService articleEntityService;

    @GetMapping("/hotArticleList")
    public R hotArticleList() {
        return R.ok().put(articleEntityService.hotArticleList());
    }
}
