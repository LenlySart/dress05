package com.cn.wanxi.model.product;

import com.cn.wanxi.model.BaseModel;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductParameter.java
 * @Description TODO
 * @createTime 2021年12月21日 14:40:00
 */
public class ProductParameter extends BaseModel {
//    流行元素
    private String fashionElement;
//    风格
    private String style;
//    服装版式
    private String model;
//    款式
    private String fashion;
//    衣长
    private String clothesLength;
//    地质
    private String character;
//    成分
    private String ingredient;

    public String getFashionElement() {
        return fashionElement;
    }

    public void setFashionElement(String fashionElement) {
        this.fashionElement = fashionElement;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFashion() {
        return fashion;
    }

    public void setFashion(String fashion) {
        this.fashion = fashion;
    }

    public String getClothesLength() {
        return clothesLength;
    }

    public void setClothesLength(String clothesLength) {
        this.clothesLength = clothesLength;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
