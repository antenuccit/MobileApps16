package osucse5236.chooseyourownadventure;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via account name/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mAccountView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    //Shared Preferences
    private SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mAccountView = (AutoCompleteTextView) findViewById(R.id.account);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mAccountSignInButton = (Button) findViewById(R.id.account_sign_in_button);
        Button mCreateNewButton = (Button) findViewById(R.id.create_new_account_button);

        mAccountSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mCreateNewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, CreateNewActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid account name, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        //Get Shared Preferences
        mSharedPrefs = getSharedPreferences("login", 0);

        SharedPreferences.Editor Ed=mSharedPrefs.edit();
        Ed.putString("foo","bar");
        Ed.commit();

        // Reset errors.
        mAccountView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String account = mAccountView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid account name.
        if (TextUtils.isEmpty(account)) {
            mAccountView.setError(getString(R.string.error_field_required));
            focusView = mAccountView;
            cancel = true;
        } else if (!isAccountValid(account)) {
            mAccountView.setError(getString(R.string.error_account_too_short));
            focusView = mAccountView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(account, password, this);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isAccountValid(String account) {
        return account.length()>2;
    }

    private boolean isPasswordValid(String password) {
        return password.length()>2;
    }

    public void login(String account){
        //Get Character Preferences
        SharedPreferences characterPrefs = getSharedPreferences(account, 0);

        //check if a character has been created for the account
        if(!characterPrefs.contains("name")){
            Intent myIntent = new Intent(LoginActivity.this, InfoInputActivity.class);
            myIntent.putExtra("account", account);
            LoginActivity.this.startActivity(myIntent);
        }
        else {
            Intent myIntent = new Intent(LoginActivity.this, ChoiceMenu.class);
            myIntent.putExtra("account", account);
            LoginActivity.this.startActivity(myIntent);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mAccount;
        private final String mPassword;
        public LoginActivity mLoginActivity;

        UserLoginTask(String account, String password, LoginActivity activity) {
            mAccount = account;
            mPassword = password;
            mLoginActivity = activity;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //Check if credentials match valid account in login user preferences
            return (mSharedPrefs.contains(mAccount) &&  mPassword.equals(mSharedPrefs.getString(mAccount, null)));

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                mLoginActivity.login(mAccount);
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_login));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
