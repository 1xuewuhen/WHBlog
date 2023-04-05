package com.xwh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.LinkEntity;
import com.xwh.service.LinkEntityService;
import com.xwh.mapper.LinkEntityMapper;
import com.xwh.whblogcommon.constants.SystemConstants;
import com.xwh.whblogcommon.utils.BeanCopyUtils;
import com.xwh.whblogcommon.vo.LinkVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈方银
* @description 针对表【wh_link(友链)】的数据库操作Service实现
* @createDate 2023-04-05 12:10:13
*/
@Service
public class LinkEntityServiceImpl extends ServiceImpl<LinkEntityMapper, LinkEntity>
    implements LinkEntityService{


    @Override
    public List<LinkVo> getAllLink() {
        List<LinkEntity> linkEntities = this.list(new LambdaQueryWrapper<LinkEntity>()
                .eq(LinkEntity::getStatus, SystemConstants.LINK_STATUS_NORMAL));

        return BeanCopyUtils.copyList(linkEntities, LinkVo.class);
    }
}




