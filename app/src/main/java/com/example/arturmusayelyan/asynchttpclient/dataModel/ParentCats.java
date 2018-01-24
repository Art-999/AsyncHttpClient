package com.example.arturmusayelyan.asynchttpclient.dataModel;

/**
 * Created by artur.musayelyan on 23/01/2018.
 */
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@SuppressLint("ParcelCreator")
public class ParentCats implements Parcelable,Serializable {
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

    protected ParentCats(Parcel in) {
        categoryId = in.readString();
        categoryName = in.readString();
        categoryImage = in.readString();
        if (in.readByte() == 0) {
            categoryCount = null;
        } else {
            categoryCount = in.readInt();
        }
    }

    public static final Creator<ParentCats> CREATOR = new Creator<ParentCats>() {
        @Override
        public ParentCats createFromParcel(Parcel in) {
            return new ParentCats(in);
        }

        @Override
        public ParentCats[] newArray(int size) {
            return new ParentCats[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryId);
        dest.writeString(categoryName);
        dest.writeString(categoryImage);
        if (categoryCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryCount);
        }
    }
}
