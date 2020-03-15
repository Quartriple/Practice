package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity {
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        RequestQueue queue;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_news);
            recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)


            queue = Volley.newRequestQueue(this);
            getNews();
        }

        public void getNews(){
            // Instantiate the RequestQueue.

            String url = "http://newsapi.org/v2/top-headlines?country=us&apiKey=e7efa750d31945a9b79d492256555457";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

                            try {
                                    JSONObject jsonObj = new JSONObject(response);

                                    JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                                    List<NewsData> news = new ArrayList<>();

                                    for(int i = 0; i < arrayArticles.length(); i++ ){
                                        JSONObject obj = arrayArticles.getJSONObject(i);



                                        NewsData newsData = new NewsData();
                                        newsData.setContent( obj.getString("description"));
                                        newsData.setTitle(obj.getString("title"));
                                        newsData.setUrlToImage(obj.getString("urlToImage"));
                                        newsData.setUrl(obj.getString("url"));
                                        news.add(newsData);
                                    }

                                    mAdapter = new MyAdapter(news, NewsActivity.this, new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            if(v.getTag() != null){
                                                int position = (int)v.getTag();
                                                ((MyAdapter)mAdapter).getNews(position);

                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((MyAdapter)mAdapter).getNews(position).getUrl()));
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                    recyclerView.setAdapter(mAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }
    }


