package com.example.unitx;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONHandler {

    private JSONObject jsonObjects;

    JSONHandler(Context context){
        try {
            //Loading json file
            InputStream inputStream=context.getAssets().open("units.json");
            byte[] json_buffer=new byte[inputStream.available()];
            inputStream.read(json_buffer);
            inputStream.close();
            //Fetching json data
            String json=new String(json_buffer, StandardCharsets.UTF_8);
            jsonObjects=new JSONObject(json);
        } catch (Exception e) {
            Log.e("JSONError", "unitJson: ",e );
        }
    }

    public ArrayList<String> unitJson(){
        ArrayList<String> layer_keys=new ArrayList<>();
        Iterator<String> layer1_keys=jsonObjects.keys();
        while (layer1_keys.hasNext()){
            layer_keys.add(layer1_keys.next());
        }
        return layer_keys;
    }

    public ArrayList<String> innerJson(String object_key) {
        ArrayList<String> layer_keys=new ArrayList<>();
        try {
            JSONObject inner_jsonObjects = jsonObjects.getJSONObject(object_key);
            Iterator<String> layer2_keys= inner_jsonObjects.keys();
            while (layer2_keys.hasNext()){
                layer_keys.add(layer2_keys.next());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return layer_keys;
    }

    public JSONObject getInnerJSONValues(String object_key,String inner_object_key){
        JSONObject double_inner_jsonObjects;
        try {
            double_inner_jsonObjects =(jsonObjects.getJSONObject(object_key)).getJSONObject(inner_object_key);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return double_inner_jsonObjects;
    }

}
