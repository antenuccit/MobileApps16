package osucse5236.chooseyourownadventure;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by thomasantenucci on 10/16/16.
 */
public class ScenarioLibrary {

    public static Map<Integer, Scenario> mMatrix = new ArrayMap<Integer, Scenario>();

    public void put(Integer scenId, Scenario scenario) {
        mMatrix.put(scenId, scenario);
    }

    public Scenario getScenario(Integer id){
        return mMatrix.get(id);
    }


}
