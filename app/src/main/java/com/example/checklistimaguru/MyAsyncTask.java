package com.example.checklistimaguru;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("StaticFieldLeak")
class MyAsyncTask extends AsyncTask<String, String, String> {
    RecyclerView recyclerView;
    private Context context;
    private String day;
    private String answerHTTP;
    private String userID;
    private String webHook;
    private String query;
    private String filter;
    private String server;

    MyAsyncTask(Date d, Context context, RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT);
        this.day = dateFormat.format(d);
        userID = "" + "/";
        webHook = "";//webhook является конфиденциальной информацией
        query = "/lists.element.get/?IBLOCK_TYPE_ID=lists&IBLOCK_ID=171";
        filter = "&FILTER[%3EPROPERTY_1491]=" + day + "%2000:00:00&FILTER[%3CPROPERTY_1491]=" + day + "%2023:59:59";
        server = "https://imaguru.bitrix24.by/rest/" + userID + webHook + query + filter;
    }
    MyAsyncTask(String name, Context context, RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
        userID = "" + "/";
        webHook = "";
        query = "/lists.element.get/?IBLOCK_TYPE_ID=lists&IBLOCK_ID=171";
        filter = "&FILTER[NAME]=%25"+name+"%25";
        server = "https://imaguru.bitrix24.by/rest/"+userID+webHook+query+filter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(server);
        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                answerHTTP = EntityUtils.toString(entity);
            } else
                answerHTTP = String.valueOf(response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException ignored) {
        } catch (IOException ignored) {
        } catch (RuntimeException ignored){}
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        TextView t;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        gson.fieldNamingStrategy();
        CheckListsArray checkListsArray = gson.fromJson(answerHTTP, CheckListsArray.class);
        super.onPostExecute(result);
        DataAdapter adapter = new DataAdapter(context, checkListsArray);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
           if (adapter.getItemCount() == 0) {
               t = ((MainActivity)context).findViewById(R.id.infoText);
               t.setText(R.string.emptyListInfo);
               t.setVisibility(View.VISIBLE);
        }
    }
}