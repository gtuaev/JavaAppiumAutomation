package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomPageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{

    @Test
    public void testPassThroudhWelcom()
    {
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomPageObject WelcomPageObject = new WelcomPageObject(driver);

        WelcomPageObject.waitForLearnMoreLink();
        WelcomPageObject.clickNextButtton();

        WelcomPageObject.waitForNewWayToExploreText();
        WelcomPageObject.clickNextButtton();

        WelcomPageObject.waitForAddOrEditPreferredLangText();
        WelcomPageObject.clickNextButtton();

        WelcomPageObject.waitForLearnMoreAboutDataCollected();
        WelcomPageObject.clickGetStartedButtton();
    }

}
