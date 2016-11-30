package osucse5236.chooseyourownadventure;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleUnitTest {

   /* @Rule
    public ActivityTestRule<ChoiceMenu> mActivityRule = new ActivityTestRule<>(ChoiceMenu.class);*/

    @Rule
    public ActivityTestRule<CombatActivity> mActivityRule = new ActivityTestRule<>(CombatActivity.class);

    @Test
    public void trueTest(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void falseTest(){
        Assert.assertEquals(0,1);
    }

    //Test Combat Timer AutoDecrements at 1 second intervals for combat
    @Test
    public void testCombatTimer() throws InterruptedException {
        CombatActivity testCombatActivity = mActivityRule.getActivity();
        int currentTime = testCombatActivity.time;
        Thread.sleep(1000);
        int delayedTime = testCombatActivity.time;
        boolean hasChanged = false;
        if (currentTime > delayedTime){
            hasChanged = true;
        }
        Assert.assertEquals(hasChanged, true);
    }

    //Test Combat Timer Will Not Decrement below 0 seconds
    @Test
    public void testCombatTimer2() throws InterruptedException {
        CombatActivity testCombatActivity = mActivityRule.getActivity();
        testCombatActivity.time = 1;
        Thread.sleep(2000);
        boolean hasChanged = false;
        TextView time = (TextView) testCombatActivity.findViewById(R.id.timer);
        if (time.getText().toString().equals("0")){
            hasChanged = true;
        }
        Assert.assertEquals(hasChanged, true);
    }

    /*@Test
    public void testUpdateChoiceMenu() throws Exception {
        ChoiceMenu testChoiceMenu = mActivityRule.getActivity();
        testChoiceMenu.mCurrentID = 0;
        String oldOptionOne = testChoiceMenu.mOptionOne.getText().toString();
        String oldOptionTwo = testChoiceMenu.mOptionTwo.getText().toString();
        String oldOptionThree = testChoiceMenu.mOptionThree.getText().toString();

        testChoiceMenu.mCurrentID = 3;
        testChoiceMenu.updateChoiceMenu();

        String newOptionOne = testChoiceMenu.mOptionOne.getText().toString();
        String newOptionTwo = testChoiceMenu.mOptionTwo.getText().toString();
        String newOptionThree = testChoiceMenu.mOptionThree.getText().toString();

        boolean isNewOptions = true;

        if (oldOptionOne.equals(newOptionOne) || oldOptionTwo.equals(newOptionTwo) || oldOptionThree.equals(newOptionThree)){
            isNewOptions = false;
        }
        assertEquals(isNewOptions, true);
    }*/
}