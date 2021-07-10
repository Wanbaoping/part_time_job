package com.baoge.service.impl;

import com.baoge.dao.CardImageDao;
import com.baoge.entity.CardImage;
import com.baoge.queryvo.CardImageVo;
import com.baoge.service.CardImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardImageServiceImpl implements CardImageService {
    @Autowired
    CardImageDao cardImageDao;
    @Override
    public CardImage getCardImageByUserid(Integer id) {
        return cardImageDao.getCardImageByUserid(id);
    }

    @Override
    public Integer addCardImage(CardImage cardImage) {
        cardImage.setStatus(1);
        return cardImageDao.addCardImage(cardImage);
    }

    @Override
    public Integer editCardImage(CardImage cardImage) {
        cardImage.setStatus(1);
        return  cardImageDao.editCardImage(cardImage);
    }

    @Override
    public List<CardImageVo> listUserIdCard() {
        return cardImageDao.listUserIdCard();
    }

    @Override
    public Integer setStatus(int id, int i) {
        return cardImageDao.setStatus(id,i);
    }

    @Override
    public List<CardImageVo> searchUserIdCard(String name, Integer roleId, Integer status) {
        return cardImageDao.searchUserIdCard(name,roleId,status);
    }

}
