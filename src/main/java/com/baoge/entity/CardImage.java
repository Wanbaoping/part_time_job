package com.baoge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardImage {
    private Integer id;
    private Integer userId;
    private String identityCardImageUrl;
    private String imageUrl;
    private Integer status;
}
