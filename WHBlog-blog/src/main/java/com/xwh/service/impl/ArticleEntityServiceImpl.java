package com.xwh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.ArticleEntity;
import com.xwh.service.ArticleEntityService;
import com.xwh.mapper.ArticleEntityMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈方银
* @description 针对表【wh_article(文章表)】的数据库操作Service实现
* @createDate 2023-04-01 21:57:34
*/
@Service
public class ArticleEntityServiceImpl extends ServiceImpl<ArticleEntityMapper, ArticleEntity>
    implements ArticleEntityService{

}




