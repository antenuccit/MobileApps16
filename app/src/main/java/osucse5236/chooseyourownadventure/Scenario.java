package osucse5236.chooseyourownadventure;

import java.util.Map;

/**
 * Created by thomasantenucci on 10/16/16.
 */
public class Scenario {

    public int mScenarioID;
    public String mScenarioText;
    public WhereTo mOptions;

    public int getScenarioID(){return mScenarioID;}
    public String getScenarioText(){return mScenarioText;}
    public WhereTo getOptions(){return mOptions;}

    public Scenario(int scenarioID, String scenarioText, WhereTo options){
        mScenarioID = scenarioID;
        mScenarioText = scenarioText;
        mOptions = options;
    }

}
