package com.baoge.dao;

import com.baoge.entity.CardImage;
import com.baoge.queryvo.CardImageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CardImageDao {
    CardImage getCardImageByUserid(Integer id);

    Integer addCardImage(@Param("cardImage") CardImage cardImage);

    Integer editCardImage(CardImage cardImage);

    List<CardImageVo> listUserIdCard();

    Integer setStatus(@Param("id") int id,@Param("i") int i);

    List<CardImageVo> searchUserIdCard(@Param("name") String name, @Param("roleId") Integer roleId,@Param("status")  Integer status);
}
