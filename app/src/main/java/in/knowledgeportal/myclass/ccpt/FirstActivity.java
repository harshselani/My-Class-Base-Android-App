package in.knowledgeportal.myclass.ccpt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import in.knowledgeportal.myclass.ccpt.Utilities.DBHelper;
import in.knowledgeportal.myclass.ccpt.Utilities.Functions;
import in.knowledgeportal.myclass.ccpt.Utilities.VolleyMethods;

/**
 * Created by harsh on 03-06-2015.
 */
public class FirstActivity extends Activity{

    private View progressView;
    final DBHelper dbhelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        String app_state = dbhelper.getOptionValue(1);
        Integer i = Integer.parseInt(app_state);
        Log.v("first", app_state);

        i=1;






        if(i==0)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            this.startActivity(intent);
        }

        if(i==-1)
        {
            Intent intent = new Intent(this,SecondLoginActivity.class);
            this.startActivity(intent);
        }

        if(i==1)
        {
            progressView = findViewById(R.id.main_login_progress);

            Functions.showProgressSingleViews(true, this, progressView);

            ArrayMap vars = new ArrayMap(7);

            vars.put("topper",1);
            vars.put("test",1);
            vars.put("student",1);
            vars.put("note",1);
            vars.put("announcement",1);
            vars.put("marks", 1);

           // Log.v("volley", "var " + vars.toString());

            VolleyMethods volleyMethods = new VolleyMethods(vars);



            String imei = "28efce85419ae6e";
            String batchID = "8";
            String classID = "12";
            String studentID = "1454";

            RequestQueue rq = Volley.newRequestQueue(FirstActivity.this);
            Context context = FirstActivity.this;

            volleyMethods.getMarks(context,progressView,imei, classID, studentID, rq, dbhelper);
            volleyMethods.getAnnouncement( context, progressView, imei, classID, batchID, rq, dbhelper);
            volleyMethods.getNotes( context, progressView, imei, classID, batchID, rq, dbhelper);
            volleyMethods.getStudents( context, progressView, imei, classID, batchID, rq, dbhelper);
            volleyMethods.getTests(context, progressView, imei, classID, batchID, rq, dbhelper);
            volleyMethods.getToppers(context, progressView, imei, classID, "852", rq, dbhelper);

            LoadDataTask loadDataTask = new LoadDataTask(vars);
            loadDataTask.execute((Void) null);

        }


    }

    public class LoadDataTask extends AsyncTask<Void, Void, Boolean> {

        private final ArrayMap vars;


        LoadDataTask(ArrayMap var) {
            vars = var;

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //this is where you should write your authentication code
            // or call external service
            // following try-catch just simulates network access

            while (!vars.isEmpty())
            {

            }

            return true;

        }

        @Override
        protected void onPostExecute(final Boolean success) {

            startMainApp();

        }


    }

    private void startMainApp() {

        progressView = findViewById(R.id.main_login_progress);
        Functions.showProgressSingleViews(false, this, progressView);


    }
}
