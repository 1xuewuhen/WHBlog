package com.xwh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.CategoryEntity;
import com.xwh.mapper.CategoryEntityMapper;
import com.xwh.service.CategoryEntityService;
import org.springframework.stereotype.Service;

/**
* @author 陈方银
* @description 针对表【wh_category(分类表)】的数据库操作Service实现
* @createDate 2023-04-05 09:10:18
*/

@Service
public class CategoryEntityServiceImpl extends ServiceImpl<CategoryEntityMapper,CategoryEntity> implements CategoryEntityService{

}




