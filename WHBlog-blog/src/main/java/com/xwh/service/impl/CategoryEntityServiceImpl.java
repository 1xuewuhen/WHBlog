package com.xwh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.ArticleEntity;
import com.xwh.entity.CategoryEntity;
import com.xwh.mapper.CategoryEntityMapper;
import com.xwh.service.ArticleEntityService;
import com.xwh.service.CategoryEntityService;
import com.xwh.whblogcommon.vo.CategoryVo;
import com.xwh.whblogcommon.constants.SystemConstants;
import com.xwh.whblogcommon.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author 陈方银
* @description 针对表【wh_category(分类表)】的数据库操作Service实现
* @createDate 2023-04-05 09:10:18
*/

@Service
public class CategoryEntityServiceImpl extends ServiceImpl<CategoryEntityMapper,CategoryEntity> implements CategoryEntityService{

    @Autowired
    private ArticleEntityService articleEntityService;

    @Autowired
    private CategoryEntityMapper categoryEntityMapper;

    @Override
    public List<CategoryVo> getCategoryList() {
        Set<Long> categoryIds = articleEntityService.list(new LambdaQueryWrapper<ArticleEntity>().eq(ArticleEntity::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL))
                .stream().map(ArticleEntity::getId).collect(Collectors.toSet());
        List<CategoryEntity> categoryEntities = this.listByIds(categoryIds).stream()
                .filter(categoryEntity -> SystemConstants.STATUS_NORMAL.equals(categoryEntity.getStatus()))
                .collect(Collectors.toList());
        return BeanCopyUtils.copyList(categoryEntities, CategoryVo.class);
    }

}




