package osucse5236.chooseyourownadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;


public class ChoiceMenu extends AppCompatActivity {

    private static final String TAG = "ChoiceMenu";

    public Button mOptionOne;
    public Button mOptionTwo;
    public Button mOptionThree;
    public Button mOptionFour;

    public TextView mStoryText;

    public ScenarioLibrary lib;

    public WhereTo tempWT = new WhereTo {
        tempWT.add("Look around",1);
        tempWT.add("Think about the situation",2);
        tempWT.add("Try attacking the wall",3);
        tempWT.add("Nothing",0);
    };



    public Scenario tempScen = new Scenario(0,"You wake up in an empty room. What do you do?", tempWT);

    public Scenario[] testScenarios = new Scenario[]{};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_choice_menu);


    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
