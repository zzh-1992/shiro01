/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.shiro.mapper;

import com.grapefruit.shiro.entity.Grape;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-24 11:38 下午
 */
@Mapper
public interface GrapeMapper {

    String column = "id,name,num,content";

    Grape selectGrape(String name);

    int saveGrape(Grape grape);
    //@Select("select " + column + " from grape where id = #{id}")
    //Grape selectGrape(int id);
}
