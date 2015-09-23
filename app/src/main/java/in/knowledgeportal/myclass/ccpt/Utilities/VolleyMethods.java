package in.knowledgeportal.myclass.ccpt.Utilities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.knowledgeportal.myclass.ccpt.SecondLoginActivity;


/**
 * Created by Harsh on 17-05-2015.
 */
public class VolleyMethods {

    private ArrayMap vars;

    public VolleyMethods(ArrayMap vars) {
        this.vars = vars;
    }

    public static void getVolleyJsonObject(final TextView tv, String url, RequestQueue rq) {
        String temp = null;

        final GsonBuilder builder = new GsonBuilder();
        //Object o;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, temp,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Object o = builder.create().fromJson(String.valueOf(response), Object.class);

                        tv.setText(o.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Adding request to request queue
        rq.add(jsonObjReq);
    }

    public static void getVolleyString(final TextView tv, String url, RequestQueue rq) {
        StringRequest postReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv.setText(response); // We set the response data in the TextView
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");

            }
        }) ;


        rq.add(postReq);
    }

    public static void loginUser(final Context context,final TextView tv,final String username, final String password,final String imei, final RequestQueue rq) {

        String url =" /sagar/contact_app/json/userLogin.php";
        String url1 = "http://api.androidhive.info/volley/person_object.json";
        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String temp_res = processResponse(response);
                tv.setText(temp_res); // We set the response data in the TextView
                Toast.makeText(context,username+":"+password+":"+imei,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error [" + error + "]");

            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("imei", imei);
                return params;
            }};



        rq.add(postReq);
    }

    public static void loginUser1(final Context context,final TextView tv,final String username, final String password,final String imei, final RequestQueue rq) {

        String url =" /sagar/contact_app/json/userLogin.php";
        String url1 = "http://api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String temp_res = null;
                        try {
                            temp_res = response.getString("classID");
                        } catch (JSONException e) {
                            Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

                        }
                        tv.setText(temp_res); // We set the response data in the TextView
                        Toast.makeText(context,username+":"+password+":"+imei,Toast.LENGTH_LONG).show();





                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("imei", imei);
                return params;
            }};


// Adding request to request queue
        rq.add(jsonObjReq);
    }

    private static String processResponse(String response) {
        final GsonBuilder builder = new GsonBuilder();

        Object o = builder.create().fromJson(String.valueOf(response), Object.class);

        try {
            JSONObject reader = new JSONObject(response);
            String temp = reader.getString("classID");
            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
            return "null";
        }


    }

    public static void registerMobile(final Context context, final TextView tv, final View loginFormView, final View progressView, final String mobile, final String classID, final String imei, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/verifyStudent.php";

        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {


                    reader = new JSONObject(response);
                    String studentexists = reader.getString("studentexists");

                    if (Integer.parseInt(studentexists)==1)
                    {
                        String studentID = reader.getString("studentID");
                        String batchID = reader.getString("batchID");
                        String code = reader.getString("code");
                        String name = reader.getString("name");

                        dbhelper.enterStudentData(studentID, batchID, code, mobile, name);
                        //tv.setText("Success");

                        //Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(context,SecondLoginActivity.class);
                        context.startActivity(intent);


                    }

                    if (Integer.parseInt(studentexists)==0) {

                        tv.setText("Please check the number entered!!");
                        return;
                    }

                    if (Integer.parseInt(studentexists)==-1) {

                        tv.setText("Error!!! Contact Administrator");
                        return;
                    }

                    Functions.showProgressBothViews(false, context, loginFormView, progressView);

                } catch (JSONException e) {


                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");
                tv.setText("Error!!! Please check internet connection & try again!!");
                Functions.showProgressBothViews(false, context, loginFormView, progressView);


            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("studentno", mobile);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);
        rq.add(postReq);
    }

    public void getMarks(final Context context, final View progressView, final String imei, final String classID, final String studentID, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/readMark.php";

        if(vars.isEmpty()) {
            Log.v("volley", "start marks spinner");
            Functions.showProgressSingleViews(true, context, progressView);
        }



        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {

                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        JSONArray marks_array = reader.getJSONArray("data");

                        dbhelper.enterMarks(marks_array);

                        Log.v("volley", "marks done");

                        vars.remove("marks");

                        Log.v("volley", "var "+ vars.toString());




                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop marks spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }





                    }else
                    {
                        Log.v("volley","marks empty");

                        vars.remove("marks");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop marks spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.v("volley", "Error");

                    vars.remove("marks");

                    Log.v("volley", "var " + vars.toString());


                    if(vars.isEmpty())
                    {
                        Log.v("volley", "stop marks spinner");
                        Functions.showProgressSingleViews(true, context, progressView);


                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");

                Log.v("volley","Internet Down");

                vars.remove("marks");


                if(vars.isEmpty())
                {
                    Log.v("volley", "stop marks spinner");
                    Functions.showProgressSingleViews(true, context, progressView);


                }


            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("studentID", studentID);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);

        rq.add(postReq);
    }

    public void getAnnouncement( final Context context, final View progressView,final String imei, final String classID, final String batchID, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/readAnnouncement.php";

        if(vars.isEmpty()) {
            Log.v("volley", "start announcement spinner");
            Functions.showProgressSingleViews(true, context, progressView);
        }



        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {

                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        JSONArray data_array = reader.getJSONArray("data");

                        dbhelper.enterAnnouncements(data_array);

                        Log.v("volley", "announcements done");

                        vars.remove("announcement");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop announcement spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }

                    }
                    else
                    {
                        Log.v("volley","announcements empty");

                        vars.remove("announcement");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop announcement spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                    Log.v("volley","Error announ" +response);

                    vars.remove("announcement");


                    if(vars.isEmpty())
                    {
                        Log.v("volley", "stop announcement spinner");
                        Functions.showProgressSingleViews(true, context, progressView);


                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");

                Log.v("volley","Internet Down");

                vars.remove("announcement");


                if(vars.isEmpty())
                {
                    Log.v("volley", "stop announcement spinner");
                    Functions.showProgressSingleViews(true, context, progressView);


                }


            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("batchID", batchID);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);

        rq.add(postReq);
    }

    public  void getNotes( final Context context, final View progressView,final String imei, final String classID, final String batchID, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/readNote.php";

        if(vars.isEmpty()) {
            Log.v("volley", "start note spinner");
            Functions.showProgressSingleViews(true, context, progressView);
        }




        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {

                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        JSONArray data_array = reader.getJSONArray("data");

                        dbhelper.enterNotes(data_array);


                        Log.v("volley", "notes done");

                        vars.remove("note");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop note spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }


                    }
                    else
                    {

                        Log.v("volley","notes empty");

                        vars.remove("note");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop note spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                    Log.v("volley","Error");

                    vars.remove("note");


                    if(vars.isEmpty())
                    {
                        Log.v("volley", "stop note spinner");
                        Functions.showProgressSingleViews(true, context, progressView);


                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");

                Log.v("volley","Internet Down");

                vars.remove("note");


                if(vars.isEmpty())
                {
                    Log.v("volley", "stop note spinner");
                    Functions.showProgressSingleViews(true, context, progressView);


                }


            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("batchID", batchID);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);

        rq.add(postReq);
    }

    public  void getStudents( final Context context, final View progressView, final String imei, final String classID, final String batchID, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/readStudent.php";


        if(vars.isEmpty()) {
            Log.v("volley", "start student spinner");
            Functions.showProgressSingleViews(true, context, progressView);
        }





        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //tv.setText(response);
                JSONObject reader = null;
                try {

                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        JSONArray data_array = reader.getJSONArray("data");

                        dbhelper.enterStudents(data_array);

                        //Toast.makeText(context,"Error in sysdfhdfh",Toast.LENGTH_LONG).show();
                        Log.v("volley","students done");

                        vars.remove("student");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop student spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }



                    }
                    else
                    {

                        Log.v("volley","students empty");

                        vars.remove("student");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop student spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }
                    }
                    //else Toast.makeText(context,"Error in sys",Toast.LENGTH_LONG).show();



                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.v("volley","Error");

                    vars.remove("student");


                    if(vars.isEmpty())
                    {
                        Log.v("volley", "stop student spinner");
                        Functions.showProgressSingleViews(true, context, progressView);


                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("Error ["+error+"]");

                Log.v("volley","Internet Down");

                vars.remove("student");


                if(vars.isEmpty())
                {
                    Log.v("volley", "stop student spinner");
                    Functions.showProgressSingleViews(true, context, progressView);


                }

            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("batchID", batchID);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);

        rq.add(postReq);
    }

    public  void getTests( final Context context, final View progressView,final String imei, final String classID, final String batchID, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/readTest.php";

        if(vars.isEmpty()) {
            Log.v("volley", "start test spinner");
            Functions.showProgressSingleViews(true, context, progressView);
        }



        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {

                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        JSONArray data_array = reader.getJSONArray("data");

                        dbhelper.enterTests(data_array);


                        Log.v("volley", "tests done");

                        vars.remove("test");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop test spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }

                    }
                    else
                    {

                        Log.v("volley","tests empty");

                        vars.remove("test");


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop test spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                    Log.v("volley", "Error");

                    vars.remove("test");

                    Log.v("volley", "var " + vars.toString());


                    if(vars.isEmpty())
                    {
                        Log.v("volley", "stop test spinner");
                        Functions.showProgressSingleViews(true, context, progressView);


                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");

                Log.v("volley","Internet Down");

                vars.remove("test");


                if(vars.isEmpty())
                {
                    Log.v("volley", "stop test spinner");
                    Functions.showProgressSingleViews(true, context, progressView);


                }



            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("batchID", batchID);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);

        rq.add(postReq);
    }

    public  void getToppers( final Context context, final View progressView, final String imei, final String classID, final String testID, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/readToppers.php";

        if(vars.isEmpty()) {
            Log.v("volley", "start topper spinner");
            Functions.showProgressSingleViews(true, context, progressView);
        }



        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {

                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        JSONArray data_array = reader.getJSONArray("data");

                        dbhelper.enterToppers(data_array);

                        Log.v("volley", "toppers done " + vars.toString());

                        vars.remove("topper");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop topper spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }



                    }
                    else
                    {

                        Log.v("volley","toppers empty");

                        vars.remove("topper");

                        Log.v("volley", "var " + vars.toString());


                        if(vars.isEmpty())
                        {
                            Log.v("volley", "stop topper spinner");
                            Functions.showProgressSingleViews(true, context, progressView);


                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.v("volley","Error");

                    vars.remove("topper");


                    if(vars.isEmpty())
                    {
                        Log.v("volley", "stop topper spinner");
                        Functions.showProgressSingleViews(true, context, progressView);


                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");

                Log.v("volley","Internet Down");

                vars.remove("topper");


                if(vars.isEmpty())
                {
                    Log.v("volley", "stop topper spinner");
                    Functions.showProgressSingleViews(true, context, progressView);


                }


            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("testID", testID);
                params.put("classID", classID);
                params.put("imei", imei);
                return params;
            }};

        postReq.setShouldCache(false);

        rq.add(postReq);
    }

    public static void testData(final Context context, final RequestQueue rq, final DBHelper dbhelper) {

        String url =" /sagar/json/test_data.php";

        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {


                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {


                        JSONArray marks_array = reader.getJSONArray("data");
                        JSONObject json_data = marks_array.getJSONObject(10);

                        //Integer i = Integer.getInteger(json_data.getString("id"));
                        String i =json_data.getString("name");


                        // List<Marks> marks = new ArrayList<>();


                       // dbhelper.enterMarks(marks_array);

                        Toast.makeText(context,"Success "+ i,Toast.LENGTH_LONG).show();


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ["+error+"]");


            }
        });

        postReq.setShouldCache(false);



        rq.add(postReq);
    }


    public static void confirmStudent(final Context context,final TextView tv, final View loginFormView, final View progressView, final String classID, final String android_id, RequestQueue rq, final DBHelper dbhelper) {
        String url =" /sagar/json/confirmStudent.php";

        final StringRequest postReq = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject reader = null;
                try {


                    reader = new JSONObject(response);
                    String result = reader.getString("result");

                    if (Integer.parseInt(result)==1)
                    {
                        dbhelper.updateOption(1,"1");

                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();


                    }else{

                       // Toast.makeText(context,"Please try again later!!",Toast.LENGTH_LONG).show();
                        tv.setText("Please try again later. Incase problem persists contact administrator!!");

                                            }



                } catch (JSONException e) {
                    e.printStackTrace();

                    tv.setText("Please try again later. Incase problem persists contact administrator!!");

                }

                Functions.showProgressBothViews(false, context, loginFormView, progressView);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error [" + error + "]");

                tv.setText("Error!!! Please check internet connection & try again!!");
                Functions.showProgressBothViews(false, context, loginFormView, progressView);


            }
        }){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("classID", classID);
                params.put("imei", android_id);
                return params;
            }};

        postReq.setShouldCache(false);



        rq.add(postReq);
    }
}
