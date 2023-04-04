package com.xwh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.ArticleEntity;
import com.xwh.mapper.ArticleEntityMapper;
import com.xwh.service.ArticleEntityService;
import com.xwh.whblogcommon.constants.SystemConstants;
import com.xwh.whblogcommon.utils.BeanCopyUtils;
import com.xwh.whblogcommon.vo.ArticleEntityVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈方银
 * @description 针对表【wh_article(文章表)】的数据库操作Service实现
 * @createDate 2023-04-01 21:57:34
 */
@Service
public class ArticleEntityServiceImpl extends ServiceImpl<ArticleEntityMapper, ArticleEntity>
        implements ArticleEntityService {

    @Override
    public List<ArticleEntityVo> hotArticleList() {
        LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<ArticleEntity>()
                .eq(ArticleEntity::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByAsc(ArticleEntity::getViewCount);
        Page<ArticleEntity> page = this.page(new Page<>(SystemConstants.DEFAULT_CURRENT_PAGE, SystemConstants.DEFAULT_SIZE), wrapper);
        return BeanCopyUtils.copyList(page.getRecords(), ArticleEntityVo.class);
    }
}




