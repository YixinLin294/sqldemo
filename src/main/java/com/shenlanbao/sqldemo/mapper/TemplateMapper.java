package com.shenlanbao.sqldemo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateMapper {

    @Select("select id from `verbal_trick_template_title` where title like concat('%',#{title},'%') and tab_id = #{tabId} ")
    Integer getTitleId(@Param("title") String title, @Param("tabId") Integer tabId);
}
