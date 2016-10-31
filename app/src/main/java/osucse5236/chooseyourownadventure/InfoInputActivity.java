package osucse5236.chooseyourownadventure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoInputActivity extends Activity {

    Button mGoogleLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        mGoogleLoc = (Button) findViewById(R.id.google_location);
        mGoogleLoc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Google Location Services OnClick Logic
            }
        });
    }
}
