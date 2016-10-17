package osucse5236.chooseyourownadventure;

import android.util.ArrayMap;

import java.util.Map;
import java.util.Set;

/**
 * Created by thomasantenucci on 10/16/16.
 */
public class Options {

        public int mScenarioID;
        public ArrayMap<String, Integer> mMatrix = new ArrayMap<String, Integer>();

        public void add(String option, Integer id) {
            mMatrix.put(option, id);
        }

        public void clear(){
            mMatrix.clear();
        }

        public Set<String> getIds(){
            return mMatrix.keySet();
        }

        public int get(String option, int index) {
            return mMatrix.get(option);
        }

    public Options(ArrayMap<String, Integer> item, int id) {
        mMatrix = item;
        mScenarioID = id;
    }

}
