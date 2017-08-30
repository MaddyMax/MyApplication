package com.example.maddy.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask1().execute();
        lv = (ListView) findViewById(R.id.lv_movie);

    }

    public class AsyncTask1 extends AsyncTask<String,String,List<ArtilceSetGet>>{


        @Override
        protected List<ArtilceSetGet> doInBackground(String... strings) {

            URL url;
            HttpURLConnection httpconn = null;
            InputStream stream;
            BufferedReader reader = null;
            List<ArtilceSetGet> artilceSetGetList = new ArrayList<>();

            try {
                url = new URL("https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=latest&apiKey=96b6502ae3f24543a6471e98361f47e5");
                httpconn = (HttpURLConnection) url.openConnection();
                httpconn.connect();
                stream = httpconn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                String line;
                StringBuffer buffer = new StringBuffer();
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                Log.e("data ---------", buffer.toString());

                JSONObject jsonObject =new JSONObject(String.valueOf(buffer));
                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                for(int i =0 ; i <jsonArray.length(); i++){
                    JSONObject articleObj = jsonArray.getJSONObject(i);
                    ArtilceSetGet article = new ArtilceSetGet();
                    article.setAuthor(articleObj.getString("author"));
                    article.setDescription(articleObj.getString("description"));
                    article.setTitle(articleObj.getString("title"));
                    Log.e("author ------", articleObj.getString("author") );
                    artilceSetGetList.add(article);

                }
                return artilceSetGetList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            } /*catch (JSONException e) {
                e.printStackTrace();
            }*/ catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(reader !=null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                httpconn.disconnect();

            }


            return null;
        }

        @Override
        protected void onPostExecute(List<ArtilceSetGet> artilceSetGets) {
            super.onPostExecute(artilceSetGets);
            /*  to call movie adapter */

            ArticleAdapter adpt = new ArticleAdapter(getBaseContext(),R.layout.articlerow,artilceSetGets);
            lv.setAdapter(adpt);
        }
    }




}
