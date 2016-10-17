package osucse5236.chooseyourownadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class ChoiceMenu extends AppCompatActivity {

    private static final String TAG = "ChoiceMenu";

    public Button mOptionOne;
    public Button mOptionTwo;
    public Button mOptionThree;
    public Button mStatsButton;

    public TextView mStoryText;

    public ScenarioLibrary lib;



    public static Options tempOp = new Options();
    public static Options tempOp2 = new Options();
    public static Options tempOp3 = new Options();



    static {
        tempOp.add("Look around",1);
        tempOp.add("Meditate on the situation",3);
        tempOp.add("Examine the walls",2);

        tempOp2.add("Press the Switch", 6);
        tempOp2.add("Ignore the switch",4);
        tempOp2.add("Look around", 5);
        
        tempOp3.add("DO NOT fd7",0);
        tempOp3.add("DO NOT ds",0);
        tempOp3.add("DO NOT gf",0);
    }



    public Scenario[] testScenarios = new Scenario[]{
        new Scenario(0,"You wake up in an empty room. What do you do?", tempOp),
        new Scenario(1,"Looking around the room, you don't see anything new!",tempOp),
        new Scenario(2,"You examine the walls, but you don't find anything of interest,", tempOp),
        new Scenario(3,"Your meditation helps you think clearly. When you open your eyes, you immediately notice a switch that sits in line with the wall.", tempOp2),
        new Scenario(4,"You decide not to press the switch, but you're still trapped in the room",tempOp2),
        new Scenario(5,"Upon a second inspection, you still don't find anything new in the room", tempOp2),
        new Scenario(6,"You press the switch and a door opens! What now?", tempOp3)
    };

    public int mCurrentID = 0;
    public Scenario mCurrentScenario = testScenarios[0];


    public void updateChoiceMenu(){
        Log.d(TAG,"CurrentID in Update = " + mCurrentID);
        mStoryText.setText(testScenarios[mCurrentID].mScenarioText);
        mCurrentScenario = testScenarios[mCurrentID];

        Options currentOptions = testScenarios[mCurrentID].getOptions();
        Object[] actualOptions = currentOptions.getStrings().toArray();


        mOptionOne.setText(actualOptions[0].toString());
        mOptionTwo.setText(actualOptions[1].toString());
        mOptionThree.setText(actualOptions[2].toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_choice_menu);

        mStoryText = (TextView) findViewById(R.id.story_text);

        mOptionOne = (Button) findViewById(R.id.option_1);
        mOptionOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.d(TAG, "Option 1 selected");
                Object[] currentOptions = mCurrentScenario.mOptions.getStrings().toArray();
                mCurrentID = mCurrentScenario.mOptions.get(currentOptions[0].toString());
                Log.d(TAG, "CurrentID is now: " + mCurrentID);
                updateChoiceMenu();
            }
        });
        mOptionTwo = (Button) findViewById(R.id.option_2);
        mOptionTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "Option 2 selected");
                Object[] currentOptions = mCurrentScenario.mOptions.getStrings().toArray();
                mCurrentID = mCurrentScenario.mOptions.get(currentOptions[1].toString());
                Log.d(TAG, "CurrentID is now: " + mCurrentID);
                updateChoiceMenu();
            }
        });
        mOptionThree = (Button) findViewById(R.id.option_3);
        mOptionThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "Option 3 selected");
                Object[] currentOptions = mCurrentScenario.mOptions.getStrings().toArray();
                mCurrentID = mCurrentScenario.mOptions.get(currentOptions[2].toString());
                Log.d(TAG, "CurrentID is now: " + mCurrentID);
                updateChoiceMenu();
            }
        });
        mStatsButton = (Button) findViewById(R.id.stats_button);



        updateChoiceMenu();

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
