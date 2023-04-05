package com.xwh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.entity.UserEntity;
import com.xwh.service.UserEntityService;
import com.xwh.mapper.UserEntityMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈方银
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-04-05 13:08:19
*/
@Service
public class UserEntityServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity>
    implements UserEntityService{

}




