package in.knowledgeportal.myclass.ccpt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.util.Linkify;
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
public class SecondLoginActivity extends Activity {


    private View loginFormView;
    private View progressView;
    private EditText login_activity_code;
    private TextView signUpTextView;

    final DBHelper dbhelper = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_login_activity);

        login_activity_code = (EditText) findViewById(R.id.login_activity_code);




        Button loginButton = (Button) findViewById(R.id.login_activity_submit);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                initLogin();
            }
        });

        loginFormView = findViewById(R.id.second_login_form);
        progressView = findViewById(R.id.second_login_progress);

        //adding underline and link to signup textview
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setPaintFlags(signUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Linkify.addLinks(signUpTextView, Linkify.ALL);

        signUpTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.i("LoginActivity", "Sign Up Activity activated.");
                Intent intent = new Intent(SecondLoginActivity.this,LoginActivity.class);
                SecondLoginActivity.this.startActivity(intent);
                // this is where you should start the signup Activity
                // LoginActivity.this.startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }



    /**
     * Validate Login form and authenticate.
     */
    public void initLogin() {

        String code = login_activity_code.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;

        String system_code= dbhelper.getOptionValue(4);



        if (!(code.matches("[0-9]+")) || TextUtils.isEmpty(code)||(code.length()!=4)) {
            login_activity_code.setError("Please enter 4 digit code");
            focusView = login_activity_code;
            cancelLogin = true;
        }

        if(!(code.equalsIgnoreCase(system_code)))
        {
            login_activity_code.setError("Incorrect Code!!!");
            focusView = login_activity_code;
            cancelLogin = true;
        }

        if (cancelLogin) {
            // error in login
            focusView.requestFocus();
        } else {
            // show progress spinner, and start background task to login


            Functions.showProgressBothViews(true, SecondLoginActivity.this, loginFormView, progressView);

            RequestQueue rq = Volley.newRequestQueue(SecondLoginActivity.this);
            String android_id = dbhelper.getOptionValue(8);
            String classID = dbhelper.getOptionValue(5);
            TextView tv = (TextView) findViewById(R.id.confirm_message);



            VolleyMethods.confirmStudent(SecondLoginActivity.this,tv ,loginFormView, progressView,
                    classID, android_id, rq, dbhelper);




            //passwordTextView.setError(getString(R.string.incorrect_password));
            //passwordTextView.requestFocus();
        }
    }



}
