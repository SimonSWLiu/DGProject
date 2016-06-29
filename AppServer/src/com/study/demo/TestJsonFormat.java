package com.study.demo;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

public class TestJsonFormat {

    public static void main(String[] args) {

        String test1 = "{name:John}";
        String test2 = "{'name':John}";
        String test3 = "{name:John}";

        String test4 =
                "[{isMandatory:Y,isSingle:Y,type:MS,ingredient:[{ingredientName:Shrimp,ingredientPrice:3.00},{ingredientName:Chicken,ingredientPrice:1.00},{ingredientName:Beef,ingredientPrice:2.00},{ingredientName:Tofu,ingredientPrice:0.00},{ingredientName:Vegetables,ingredientPrice:0.00}]},{isMandatory:Y,isSingle:Y,type:MS,ingredient:[{ingredientName:Shrimp,ingredientPrice:3.00},{ingredientName:Chicken,ingredientPrice:1.00},{ingredientName:Beef,ingredientPrice:2.00},{ingredientName:Tofu,ingredientPrice:0.00},{ingredientName:Vegetables,ingredientPrice:0.00}]}]";

        JSONObject jsonObj1;
        JSONObject jsonObj2;
        JSONObject jsonObj3;

        try {
            jsonObj1 = new JSONObject(test1);
            jsonObj2 = new JSONObject(test2);
            jsonObj3 = new JSONObject(test3);

//            System.out.println("jsonObj1: " + jsonObj1);
//            System.out.println("jsonObj2: " + jsonObj2);
//            System.out.println("jsonObj3: " + jsonObj3);

            JSONArray jsonArray = new JSONArray(test4);
            System.out.println(jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

//                String ingredient = jsonObject.get("ingredient").toString();
//                System.out.println(ingredient);
//
//                JSONArray jsonArray2 = new JSONArray(ingredient);
                
                JSONArray jsonArray2 = jsonObject.getJSONArray("ingredient");
                
                
                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                    System.out.println(jsonObject2.get("ingredientName"));
                }
            }


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
