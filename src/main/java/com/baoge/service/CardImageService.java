package com.baoge.service;

import com.baoge.entity.CardImage;
import com.baoge.queryvo.CardImageVo;

import java.util.List;

public interface CardImageService {
    CardImage getCardImageByUserid(Integer id);

    Integer addCardImage(CardImage cardImage);

    Integer editCardImage(CardImage cardImage);

    List<CardImageVo> listUserIdCard();

    Integer setStatus(int id,int i);

    List<CardImageVo> searchUserIdCard(String name, Integer roleId, Integer status);
}
