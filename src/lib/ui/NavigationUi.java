package lib.ui;

import io.appium.java_client.AppiumDriver;


public class NavigationUi extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUi(AppiumDriver driver)
    {
        super(driver);
    }

    public void clikcMyLists()     // Метод клика на иконку "Мои списки"
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My List",
                5
        );
    }

}
