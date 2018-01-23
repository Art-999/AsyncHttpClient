package com.example.arturmusayelyan.asynchttpclient.dataModel;

/**
 * Created by artur.musayelyan on 23/01/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentCats {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_count")
    @Expose
    private Integer categoryCount;
    @SerializedName("children_cats")
    @Expose
    private List<ChildCats> childrenCats = null;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    public List<ChildCats> getChildrenCats() {
        return childrenCats;
    }

    public void setChildrenCats(List<ChildCats> childrenCats) {
        this.childrenCats = childrenCats;
    }

//    @Override
//    public String toString() {
//        return "ParentCats{" + "categoryId='" + categoryId + '\'' + ", categoryName='" + categoryName + '\'' + ", categoryImage='" + categoryImage + '\'' + ", categoryCount=" + categoryCount + ", childrenCats=" + childrenCats + '}';
//    }

    @Override
    public String toString() {
        return categoryId+" "+categoryName+" "+categoryCount+" "+childrenCats;
    }
}
