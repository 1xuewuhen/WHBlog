package com.xwh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xwh.entity.CategoryEntity;
import com.xwh.whblogcommon.vo.CategoryVo;

import java.util.List;

/**
* @author 陈方银
* @description 针对表【wh_category(分类表)】的数据库操作Service
* @createDate 2023-04-05 09:10:18
*/
public interface CategoryEntityService extends IService<CategoryEntity> {

    List<CategoryVo> getCategoryList();
}
