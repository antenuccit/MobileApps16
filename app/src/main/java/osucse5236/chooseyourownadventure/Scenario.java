package osucse5236.chooseyourownadventure;

import java.util.Map;

/**
 * Created by thomasantenucci on 10/16/16.
 */
public class Scenario {

    public int mScenarioID;
    public String mScenarioText;
    public Options mOptions;
    public String mNewActivity;
    public int mStatRaise;

    public int getScenarioID(){return mScenarioID;}
    public String getScenarioText(){return mScenarioText;}
    public Options getOptions(){return mOptions;}
    public String getNewActivity(){return mNewActivity;}
    public int getStatRaise(){return mStatRaise;}

    public Scenario(int scenarioID, String scenarioText, Options options, String newActivity, int statRaise){
        mScenarioID = scenarioID;
        mScenarioText = scenarioText;
        mOptions = options;
        mNewActivity = newActivity;
        mStatRaise = statRaise;
    }

}
