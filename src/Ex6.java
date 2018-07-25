import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;


public class Ex6 extends CoreTestCase {
    @Test
    public void testAssertTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertElementPresent("The article page does not contain a text title");

    }
}
