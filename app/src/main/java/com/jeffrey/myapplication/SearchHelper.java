package com.jeffrey.myapplication;

import android.support.annotation.Nullable;

public class SearchHelper {

    @Nullable
    public static String getImageUrl(SearchItem searchItem) {
        if (searchItem.images == null) {
            return null;
        }
        
        for (int i = 0; i < searchItem.images.size(); i++) {
            if (searchItem.images.get(i).link != null) {
                return searchItem.images.get(i).link;
            }
        }
        return null;
    }
}
