package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    MY_CREATED_LIST = "//*[@resource-id='org.wikipedia:id/item_container']//*[@text='Test folder']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the and of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)   // Метод добавления статьи в список, который создается
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToMyCreatedList()   // Метод добавления статьи в список, который уже создан
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                10
        );

        this.waitForElementAndClick(
                By.xpath(MY_CREATED_LIST),
                "Can not find the created list to add the article",
                5
        );
    }

    public void closeArticle()                // Метод закрытия статьи
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article. cannot find X link",
                5
        );
    }

    public void assertElementPresent(String error_message)         // Метод проверки заголовка статьи без ожидания
    {
        List<WebElement> elements = driver.findElements(By.id(TITLE));
        int article_present = elements.size();
        for(WebElement element: elements)
        {
            String tittle = element.getText();
            String error = error_message + tittle;
            assertTrue (error, article_present > 0);
        }
    }


}
