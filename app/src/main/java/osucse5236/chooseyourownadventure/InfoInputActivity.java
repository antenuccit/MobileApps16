package osucse5236.chooseyourownadventure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.Geocoder;
import java.util.Locale;
import android.location.Address;
import java.util.List;
import java.util.jar.Manifest;
import android.os.Build;

import android.location.LocationManager;
import android.location.Location;
import android.content.Context;


public class InfoInputActivity extends Activity {

    Button mGoogleLoc;
    String mAccount;
    String cityName;
    String stateName;

    final int didGetPermission = 5;

    // UI references.
    private EditText mNameView;
    private EditText mHometownView;

    //Shared Preferences
    private SharedPreferences mCharacterPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_input);

        //Get Account name as an intent
        Intent intent = getIntent();
        mAccount = intent.getStringExtra("account");

        mNameView = (EditText) findViewById(R.id.name_input_box);
        mHometownView = (EditText) findViewById(R.id.hometown_placeholder);

        mGoogleLoc = (Button) findViewById(R.id.google_location);
        mGoogleLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if (ContextCompat.checkSelfPermission(InfoInputActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(InfoInputActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            didGetPermission);
                    ActivityCompat.requestPermissions(InfoInputActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                            didGetPermission);

                }
                Log.d("DID IT WORK", "onClick: UGH ");




            }
        });

        Button mCreateButton = (Button) findViewById(R.id.create_new_character);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCharacter();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d("UM", "onRequestPermissionsResult: ");
        switch (requestCode) {
            case didGetPermission: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


//                    LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//
//
//                    if (Build.VERSION.SDK_INT >= 23 &&
//                            ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
//                            ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        return  ;
//                    }
//                    //Location location = lm.requestSingleUpdate(LocationManager.GPS_PROVIDER,null);
//
//
//
//
//                    double lat = location.getLongitude();
//                    double longi = location.getLatitude();
//
//
//                    Geocoder geocoder = new Geocoder(InfoInputActivity.this, Locale.getDefault());
//                    try {
//                        List<Address> addresses = geocoder.getFromLocation(lat, longi, 0);
//                        cityName = addresses.get(0).getAddressLine(0);
//                        stateName = addresses.get(0).getAddressLine(1);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void createCharacter(){

        // Store values at the time of creating account.
        String name = mNameView.getText().toString();
        String hometown = mHometownView.getText().toString();
        int startingScenario = 0;

        // Reset errors.
        mNameView.setError(null);
        mHometownView.setError(null);

        View focusView = null;
        boolean cancel = false;

        //Create Shared Preferences
        mCharacterPrefs = getSharedPreferences(mAccount, 0);

        // Check for a valid hometown, if the user entered one.
        if (!TextUtils.isEmpty(hometown) && !isHometownValid(hometown)) {
            mHometownView.setError(getString(R.string.error_invalid_hometown));
            focusView = mHometownView;
            cancel = true;
        }

        // Check for a valid account name.
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        } else if (!isNameValid(name)) {
            mNameView.setError(getString(R.string.error_account_too_short));
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //Add name and hometown values to account
            SharedPreferences.Editor Ed=mCharacterPrefs.edit();
            Ed.putString("name",name);
            Ed.putString("hometown",hometown);
            Ed.putInt("scenario", startingScenario);
            Ed.putInt("mag",5);
            Ed.putInt("str",5);
            Ed.putInt("dex",5);
            Ed.commit();

            Intent myIntent = new Intent(InfoInputActivity.this, ChoiceMenu.class);
            myIntent.putExtra("account", mAccount);
            InfoInputActivity.this.startActivity(myIntent);
        }

    }

    private boolean isNameValid(String account) {
        return account.length() > 1;
    }

    private boolean isHometownValid(String password) {
        return password.length() > 1;
    }
}