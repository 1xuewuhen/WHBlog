package com.xwh.service;

import com.xwh.entity.LinkEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwh.whblogcommon.vo.LinkVo;

import java.util.List;

/**
* @author 陈方银
* @description 针对表【wh_link(友链)】的数据库操作Service
* @createDate 2023-04-05 12:10:13
*/
public interface LinkEntityService extends IService<LinkEntity> {

    List<LinkVo> getAllLink();
}
