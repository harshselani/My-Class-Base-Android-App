package in.knowledgeportal.myclass.ccpt;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import in.knowledgeportal.myclass.ccpt.Utilities.DBHelper;
import in.knowledgeportal.myclass.ccpt.Utilities.Functions;
import in.knowledgeportal.myclass.ccpt.Utilities.VolleyMethods;


/**
 * Android login screen Activity
 */
public class LoginActivity extends Activity {


    private View loginFormView;
    private View progressView;
    private EditText login_activity_mobile;
    private TextView signUpTextView;
    final DBHelper dbhelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login_activity_mobile = (EditText) findViewById(R.id.login_activity_mobile);

        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        dbhelper.updateOption(8, android_id);
        dbhelper.updateOption(1, "0");
        dbhelper.updateOption(4,"0");








        Button loginButton = (Button) findViewById(R.id.login_activity_next);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                initLogin();
            }
        });

        loginFormView = findViewById(R.id.first_login_form);
        progressView = findViewById(R.id.first_login_progress);


    }



    /**
     * Validate Login form and authenticate.
     */
    public void initLogin() {

        String mobile = login_activity_mobile.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;


        if (!(mobile.matches("[0-9]+")) || TextUtils.isEmpty(mobile)||(mobile.length()!=10)) {
            login_activity_mobile.setError("Please enter 10 digit mobile number");
            focusView = login_activity_mobile;
            cancelLogin = true;
        }

        if (cancelLogin) {
            // error in login
            focusView.requestFocus();
        } else {
            // show progress spinner, and start background task to login
            Functions.showProgressBothViews(true, LoginActivity.this, loginFormView, progressView);

            RequestQueue rq = Volley.newRequestQueue(LoginActivity.this);

            String classID = dbhelper.getOptionValue(5);
            String android_id = dbhelper.getOptionValue(8);
            TextView tv = (TextView) findViewById(R.id.login_message);

            VolleyMethods.registerMobile(LoginActivity.this, tv,loginFormView, progressView,
                    mobile, classID, android_id, rq, dbhelper);











        //passwordTextView.setError(getString(R.string.incorrect_password));
            //passwordTextView.requestFocus();
        }
    }



    private boolean isMobileValid(String mobile) {
        //add your own logic
        return mobile.length() == 10;
    }

    /**
     * Shows the progress UI and hides the login form.
     */



}
