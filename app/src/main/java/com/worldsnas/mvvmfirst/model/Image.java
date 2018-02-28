package com.worldsnas.mvvmfirst.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Image implements Parcelable{

    @Json(name = "previewHeight")
    abstract Integer previewHeight();
    @Json(name = "likes")
    abstract Integer likes();
    @Json(name = "favorites")
    abstract Integer favorites();
    @Json(name = "tags")
    abstract String tags();
    @Json(name = "webformatHeight")
    abstract Integer webformatHeight();
    @Json(name = "views")
    abstract Integer views();
    @Json(name = "webformatWidth")
    abstract Integer webformatWidth();
    @Json(name = "previewWidth")
    abstract Integer previewWidth();
    @Json(name = "comments")
    abstract Integer comments();
    @Json(name = "downloads")
    abstract Integer downloads();
    @Json(name = "pageURL")
    abstract String pageURL();
    @Json(name = "previewURL")
    abstract String previewURL();
    @Json(name = "webformatURL")
    abstract String webformatURL();
    @Json(name = "imageWidth")
    abstract Integer imageWidth();
    @Json(name = "user_id")
    abstract Integer userId();
    @Json(name = "user")
    abstract String user();
    @Json(name = "type")
    abstract String type();
    @Json(name = "id")
    abstract String id();
    @Json(name = "userImageURL")
    abstract String userImageURL();
    @Json(name = "imageHeight")
    abstract Integer imageHeight();

    public static JsonAdapter<Image> jsonAdapter(Moshi moshi) {
        return new AutoValue_Image.MoshiJsonAdapter(moshi);
    }
}