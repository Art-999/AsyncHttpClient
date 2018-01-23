package com.example.arturmusayelyan.asynchttpclient.utils;

import com.example.arturmusayelyan.asynchttpclient.dataModel.ChildCats;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ParentCats;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artur.musayelyan on 23/01/2018.
 */

public class JsonParser {

    public static ArrayList<ParentCats> parseResultAllJsonArrayToList(JSONArray response) {
        Gson gson = new Gson();
        List<ParentCats> parentCatsDataList = gson.fromJson(String.valueOf(response), new TypeToken<List<ParentCats>>() {
        }.getType());
        if (parentCatsDataList != null) {
            return (ArrayList<ParentCats>) parentCatsDataList;
        }
        return null;
    }

    public static ArrayList<ChildCats> parseResultChildJsonArrayToList(JSONArray response, int number) {

        List<ChildCats> childCats = parseResultAllJsonArrayToList(response).get(number).getChildrenCats();
        return (ArrayList<ChildCats>) childCats;
    }
}
