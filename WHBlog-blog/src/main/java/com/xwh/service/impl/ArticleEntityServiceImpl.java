package com.xwh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.ArticleEntity;
import com.xwh.entity.CategoryEntity;
import com.xwh.mapper.ArticleEntityMapper;
import com.xwh.mapper.CategoryEntityMapper;
import com.xwh.service.ArticleEntityService;
import com.xwh.whblogcommon.constants.SystemConstants;
import com.xwh.whblogcommon.utils.BeanCopyUtils;
import com.xwh.whblogcommon.vo.ArticleDetailVo;
import com.xwh.whblogcommon.vo.ArticleEntityListVo;
import com.xwh.whblogcommon.vo.ArticleEntityVo;
import com.xwh.whblogcommon.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 陈方银
 * @description 针对表【wh_article(文章表)】的数据库操作Service实现
 * @createDate 2023-04-01 21:57:34
 */
@Service
public class ArticleEntityServiceImpl extends ServiceImpl<ArticleEntityMapper, ArticleEntity>
        implements ArticleEntityService {

    @Autowired
    private CategoryEntityMapper categoryEntityMapper;

    @Override
    public List<ArticleEntityVo> hotArticleList() {
        LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<ArticleEntity>()
                .eq(ArticleEntity::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByAsc(ArticleEntity::getViewCount);
        Page<ArticleEntity> page = this.page(new Page<>(SystemConstants.DEFAULT_CURRENT_PAGE, SystemConstants.DEFAULT_SIZE), wrapper);
        return BeanCopyUtils.copyList(page.getRecords(), ArticleEntityVo.class);
    }

    @Override
    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<ArticleEntity>()
                .eq(Objects.nonNull(categoryId) && categoryId > 0, ArticleEntity::getCategoryId, categoryId)
                .eq(ArticleEntity::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(ArticleEntity::getIsTop);
        Page<ArticleEntity> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        Set<Long> categoryEntityIds = page.getRecords().stream().map(ArticleEntity::getCategoryId).collect(Collectors.toSet());
        Map<Long, CategoryEntity> categoryEntityMap = categoryEntityMapper.selectBatchIds(categoryEntityIds).stream().collect(Collectors.toMap(CategoryEntity::getId, categoryEntity -> categoryEntity));
        List<ArticleEntityListVo> articleEntityListVos = page.getRecords().stream().map(articleEntity -> {
            ArticleEntityListVo articleEntityListVo = BeanCopyUtils.copyBean(articleEntity, ArticleEntityListVo.class);
            if (categoryEntityMap.containsKey(articleEntity.getCategoryId())) {
                articleEntityListVo.setCategoryName(categoryEntityMap.get(articleEntity.getCategoryId()).getName());
            }
            return articleEntityListVo;
        }).collect(Collectors.toList());
        return new PageVo(articleEntityListVos, page.getTotal());
    }

    @Override
    public ArticleDetailVo getArticleDetail(Long id) {
        ArticleEntity articleEntity = this.getById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(articleEntity, ArticleDetailVo.class);
        Long categoryId = articleDetailVo.getCategoryId();
        CategoryEntity categoryEntity = categoryEntityMapper.selectById(categoryId);
        if (categoryEntity!=null){
            articleDetailVo.setCategoryName(categoryEntity.getName());
        }
        return articleDetailVo;
    }
}




