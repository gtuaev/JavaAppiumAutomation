package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']",
            CLICK_BY_ARTICLE_MY_LIST = "id:org.wikipedia:id/page_list_item_container";



    /*TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder)       // Метод замены в xpath названия папки
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)  // Метод замены в xpath заголовка статьи
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)                     // Метод клика на список
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find by folder name" + name_of_folder,
                20
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)        // Метод ожидания наличия статьи по заголовку
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title" + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)       // Метод ожидания отсутствия статьи по заголовку
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title" + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)                // Метод свайпа влево для удаления статьи по заголовку
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);

        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);

    }

    public void clicklArticleMyList()               // Метод клика по статье внутри списка
    {
        this.waitForElementAndClick(
                CLICK_BY_ARTICLE_MY_LIST,
                "Cannot open article",
                5
        );
    }

}

