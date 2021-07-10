package com.baoge.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardImageVo {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer roleId;
    private String rolename;
    private String identityCardImageUrl;
    private String imageUrl;
    private Integer status;
}
