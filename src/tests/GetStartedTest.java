package tests;

import lib.CoreTestCase;
import lib.ui.WelcomPageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{

    @Test
    public void testPassThroudhWelcom()
    {
        if (this.Platform.isAndroid()) {
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
