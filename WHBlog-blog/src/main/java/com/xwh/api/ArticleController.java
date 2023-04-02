package com.xwh.api;


import com.xwh.service.ArticleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class ArticleController {

    @Autowired
    private ArticleEntityService articleEntityService;

    @GetMapping("/test")
    public Object test() {
        return articleEntityService.list();
    }
}
