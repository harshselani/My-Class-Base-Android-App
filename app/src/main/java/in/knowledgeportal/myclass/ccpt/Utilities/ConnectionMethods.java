package in.knowledgeportal.myclass.ccpt.Utilities;

/**
 * Created by Harsh on 31-05-2015.
 */

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ConnectionMethods {


    public static void OkHttpStringTest(){


        OkHttpClient client = new OkHttpClient();
        String url = "http://api.androidhive.info/volley/person_object.json";
        Request request = new Request.Builder()
                    .url(url)
                    .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {

                    //tv.setText(response.body().string());
                    Log.v("ok", response.body().string());



                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        });


//return  s;

        }

}
