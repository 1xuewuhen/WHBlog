package com.xwh.service;

import com.xwh.entity.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwh.whblogcommon.vo.ArticleEntityVo;

import java.util.List;

/**
* @author 陈方银
* @description 针对表【wh_article(文章表)】的数据库操作Service
* @createDate 2023-04-01 21:57:34
*/
public interface ArticleEntityService extends IService<ArticleEntity> {

    List<ArticleEntityVo> hotArticleList();
}
