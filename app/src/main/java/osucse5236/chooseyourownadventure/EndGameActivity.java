package osucse5236.chooseyourownadventure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndGameActivity extends Activity {

    Button mDelete;
    Button mRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

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
