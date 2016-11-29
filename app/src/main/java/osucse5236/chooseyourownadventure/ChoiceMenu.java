package osucse5236.chooseyourownadventure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.app.Dialog;
import android.view.LayoutInflater;


import org.w3c.dom.Text;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.Inflater;


public class ChoiceMenu extends AppCompatActivity {

    private static final String TAG = "ChoiceMenu";

    private SharedPreferences mCharacterPrefs;

    public Button mOptionOne;
    public Button mOptionTwo;
    public Button mOptionThree;
    public Button mStatsButton;

    public TextView mStoryText;



    public ScenarioLibrary lib;



    public static Options tempOp = new Options();
    public static Options tempOp2 = new Options();
    public static Options tempOp3 = new Options();
    public static Options tempOp4 = new Options();
    public static Options tempOp5 = new Options();

    static {
        tempOp.add("Look around",1);
        tempOp.add("Meditate on the situation",3);
        tempOp.add("Examine the walls",2);

        tempOp2.add("Press the Switch", 6);
        tempOp2.add("Ignore the switch",4);
        tempOp2.add("Look around", 5);
        
        tempOp3.add("Stay where you are",7);
        tempOp3.add("Examine the doorway",8);
        tempOp3.add("Take the door",9);

        tempOp4.add("Take the left path",11);
        tempOp4.add("Ponder both options",10);
        tempOp4.add("Take the right path",12);

        tempOp5.add("Start again from the room",0);
        tempOp5.add("Start again from the switch in the wall",6);
        tempOp5.add("Start again from the hallway split",10);
    }



    public Scenario[] testScenarios = new Scenario[]{
        new Scenario(0,"You wake up in an empty room. What do you do?", tempOp, "no",0),
        new Scenario(1,"Looking around the room, you don't see anything new!",tempOp, "no",0),
        new Scenario(2,"You examine the walls, but you don't find anything of interest,", tempOp, "no",0),
        new Scenario(3,"Your meditation helps you think clearly. When you open your eyes, you immediately notice a switch that sits in line with the wall.", tempOp2, "no",0),
        new Scenario(4,"You decide not to press the switch, but you're still trapped in the room",tempOp2, "no",0),
        new Scenario(5,"Upon a second inspection, you still don't find anything new in the room", tempOp2,"no",0),
        new Scenario(6,"You press the switch and a wall slides aside to reveal a door! What now?", tempOp3,"no",0),
        new Scenario(7,"Cautious, you wait to see if anything rushes through the door at you, but nothing comes.",tempOp3,"no",0),
        new Scenario(8,"You examine the doorway... and find nothing of interest. It's just a doorway dude.",tempOp3,"no",0),
        new Scenario(9,"Upon exiting the doorway, you find that the hallway splits into two different paths. The left path is wreathed in shadow, and you can't see past a few feet. The right path has a strong light emitting from it's end. Which one do you take?",tempOp4,"no",0),
        new Scenario(10,"You didn't think about anything new...",tempOp4,"no",0),
        new Scenario(11,"YOU DIED",tempOp5,"no",0),
        new Scenario(12,"YOU DIED",tempOp5,"no",0)


    };

    public int mCurrentID;
    public Scenario mCurrentScenario = testScenarios[0];
    Point p;



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
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_choice_menu);
        } else {
            setContentView(R.layout.activity_choice_menu_l);
        }

        Intent intent = getIntent();
        mCharacterPrefs = getSharedPreferences(intent.getStringExtra("account"), 0);
        final SharedPreferences.Editor Ed = mCharacterPrefs.edit();

        mCurrentID = mCharacterPrefs.getInt("scenario",0);

        mStoryText = (TextView) findViewById(R.id.story_text);

        mOptionOne = (Button) findViewById(R.id.option_1);
        mOptionOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.d(TAG, "Option 1 selected");
                Object[] currentOptions = mCurrentScenario.mOptions.getStrings().toArray();
                mCurrentID = mCurrentScenario.mOptions.get(currentOptions[0].toString());

                Ed.putInt("scenario", mCurrentID);
                Ed.commit();


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

                Ed.putInt("scenario", mCurrentID);
                Ed.commit();


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


                Ed.putInt("scenario", mCurrentID);
                Ed.commit();

                Log.d(TAG, "CurrentID is now: " + mCurrentID);
                updateChoiceMenu();
            }
        });
        mStatsButton = (Button) findViewById(R.id.stats_button);
        mStatsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                final Dialog dialog = new Dialog(ChoiceMenu.this);



                dialog.setContentView(R.layout.activity_popup);
                dialog.setTitle("Character Stats");

                Button btnCancel        = (Button) dialog.findViewById(R.id.cancel);
                btnCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                    }
                });
                dialog.show();

                TextView mMagic = (TextView) dialog.findViewById(R.id.mag);
                TextView mStr = (TextView) dialog.findViewById(R.id.str);
                TextView mDex = (TextView) dialog.findViewById(R.id.dex);
                TextView mHometown = (TextView) dialog.findViewById(R.id.hometown);
                TextView mCharacterName = (TextView) dialog.findViewById(R.id.character_name);


                String charName = mCharacterPrefs.getString("name","Ooops!");

                mCharacterName.setText("Character Name: " + charName);
                mHometown.setText("Hometown: " + mCharacterPrefs.getString("hometown","Nowhere"));
                mMagic.setText("Magic = " + mCharacterPrefs.getInt("mag",5));
                mStr.setText("Strength = " + mCharacterPrefs.getInt("str",5));
                mDex.setText("Dexterity = " + mCharacterPrefs.getInt("dex",5));


            }
        });



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

//    private void showPopup(final AppCompatActivity context) {
//        int popupWidth = 200;
//        int popupHeight = 150;
//
//        // Inflate the popup_layout.xml
//        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
//        LayoutInflater layoutInflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View layout = layoutInflater.inflate(R.layout.activity_popup, viewGroup);
//
//        // Creating the PopupWindow
//        final PopupWindow popup = new PopupWindow(context);
//        popup.setContentView(layout);
//        popup.setWidth(popupWidth);
//        popup.setHeight(popupHeight);
//        popup.setFocusable(true);
//
//
//
//        // Getting a reference to Close button, and close the popup when clicked.
//        Button close = (Button) layout.findViewById(R.id.close);
//        close.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                popup.dismiss();
//            }
//        });
//    }
}
