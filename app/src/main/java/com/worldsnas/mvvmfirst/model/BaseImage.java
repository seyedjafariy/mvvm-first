package com.worldsnas.mvvmfirst.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.lang.reflect.Type;

@AutoValue
public abstract class BaseImage<T>{

    @Json(name = "totalHits")
    abstract int totalHits();
    @Json(name="hits")
    abstract T hits();
    @Json(name = "total")
    abstract int total();

    public static <T> JsonAdapter<BaseImage<T>> jsonAdapter(Moshi moshi, Type[] types) {
        return new AutoValue_BaseImage.MoshiJsonAdapter<>(moshi, types);
    }
}
