package osucse5236.chooseyourownadventure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndGameActivity extends Activity {

    Button mDelete;
    Button mRestart;

    //Shared Character Preferences
    private SharedPreferences mCharacterPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        //Get Account name as an intent, open shared character preferences
        Intent intent = getIntent();
        mCharacterPrefs = getSharedPreferences(intent.getStringExtra("account"), 0);

        mRestart = (Button) findViewById(R.id.restart);
        mRestart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Restart Button OnClick Logic
            }
        });
        mDelete = (Button) findViewById(R.id.delete_character);
        mDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Delete Button OnClick Logic
            }
        });
    }
}