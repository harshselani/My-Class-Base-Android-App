package in.knowledgeportal.myclass.ccpt.Utilities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import in.knowledgeportal.myclass.ccpt.R;


public class VolleyActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        RequestQueue rq = Volley.newRequestQueue(this);




        //final TextView tv = (TextView) findViewById(R.id.textView);
        final String url = "http://httpbin.org/post";
        String url1 = "http://api.androidhive.info/volley/person_object.json";

        //getVolleyString(tv,url,rq);


        //VolleyMethods.getVolleyJsonObject(tv, url1,rq);


    }

//    private void getVolleyJsonObject(final TextView tv, String url, RequestQueue rq) {
//        String temp = null;
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                url, temp,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        tv.setText(response.toString());
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//// Adding request to request queue
//        rq.add(jsonObjReq);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
