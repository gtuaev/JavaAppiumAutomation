import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Ex3 extends CoreTestCase
{
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        List<WebElement> search_list = SearchPageObject.waitForAllResultsPresent();
        assertTrue("Cannot find elements with class 'LinearLayout", search_list.size() > 1);

        SearchPageObject.searchClear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsDisappear();
    }

}


