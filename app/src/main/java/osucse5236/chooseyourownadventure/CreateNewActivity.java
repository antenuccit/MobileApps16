package osucse5236.chooseyourownadventure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateNewActivity extends AppCompatActivity {

    // UI references.
    private EditText mAccountView;
    private EditText mPasswordView;

    //Shared Preferences
    private SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        mAccountView = (EditText) findViewById(R.id.newAccountName);
        mPasswordView = (EditText) findViewById(R.id.newPassword);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.createNew || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        Button mCreateButton = (Button) findViewById(R.id.createButton);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });

    }

    private void createNewAccount(){

        // Store values at the time of creating account.
        String account = mAccountView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Reset errors.
        mAccountView.setError(null);
        mPasswordView.setError(null);

        View focusView = null;
        boolean cancel = false;

        //Get Shared Preferences
        mSharedPrefs = getSharedPreferences("login", 0);

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
        } else if (!isAccountUsed(account)) {
            mAccountView.setError(getString(R.string.error_account_exists));
            focusView = mAccountView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //create a new account
            SharedPreferences.Editor Ed=mSharedPrefs.edit();
            Ed.putString(account,password);
            Ed.commit();

            Intent myIntent = new Intent(CreateNewActivity.this, InfoInputActivity.class);
            myIntent.putExtra("account", account);
            CreateNewActivity.this.startActivity(myIntent);
        }

    }

    private boolean isAccountValid(String account) {
        return account.length() > 2;
    }

    private boolean isAccountUsed(String account) {
        return (!mSharedPrefs.contains(account));
    }

    private boolean isPasswordValid(String password) {
        return password.length()>2;
    }
}