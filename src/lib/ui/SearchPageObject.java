package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPageObject extends MainPageObject
{
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
            SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list",
            SEARCH_PLACEHOLDER = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULTS_ELEMENTS = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@class,'android.widget.LinearLayout')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResuiltSearchElement(String substring)       // Заменяем подстроку в xpath на вводимую нами
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput()          // Метод "нажимания" на поисковую строку на главной
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking searching init element");
    }

    public void waitForCancelButtonToAppear()         // Метод проверки наличия кнопки отмены поиска
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button!", 5);
    }
    public void waitForCancelButtonToDisappear()       // Метод проверки отсутствия кнопки отмены поиска
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch()         // Метод клика на кнопку отмены поиска

    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)        // Метод ввода поискового запроса, текст задается в переменной
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)        // Метод проверки корректной подстроки в открытой статье
    {
        String  search_result_xpath = getResuiltSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring);
    }

    public void clickByArticleWithSubstring(String substring)     // Метод проверки корректной подстроки в открытой статье
    {
        String  search_result_xpath = getResuiltSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15 );

    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public List<WebElement> waitForAllResultsPresent()             // Метод получения всех результатов поиска
    {
        return this.waitForElementsPresent(SEARCH_RESULTS_ELEMENTS,"Cannot find elements with class 'LinearLayout'", 15);
    }

    public void waitForSearchResultsDisappear()    // Метод проверки отсутствия рез-тов поиска (по блокам)
    {
        this.waitForElementNotPresent(SEARCH_RESULTS_LIST, "Search result list is still present", 5);
    }

    public void searchClear()     // Метод очистки поля поиска
    {
        this.waitForElementAndClear(SEARCH_PLACEHOLDER, "Cannot find search field to clear it", 5);
    }

}
