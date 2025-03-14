package com.example.myapplication.slot14;

import android.content.Context;
import android.view.PixelCopy;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FunctionVolley {
    public void readHtmlByVolleyStringRequest(Context context, TextView textView){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url ="https://www.google.com/";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String strKQ = s.substring(0,1000);
                textView.setText(strKQ);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText(volleyError.getMessage());
            }
        });
        requestQueue.add(request);
    }
    String strKq = "";
    public void read_json_Array_of_objects(Context context, TextView textView){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://hungnttg.github.io/array_json_new.json";
        //Mang cua cac doi tuong => JsonArrayRequest
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0 ; i<jsonArray.length(); i++){
                    //object => jsonObject
                    try{
                        JSONObject person = jsonArray.getJSONObject(i);
                        String id = person.getString("id");
                        String name = person.getString("name");
                        String email = person.getString("email");
                        strKq +="Id: "+id+" Name: "+name+" email: "+email+"\n";
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                textView.setText(strKq);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText(volleyError.getMessage());
            }
        });
        queue.add(request);
    }


}
