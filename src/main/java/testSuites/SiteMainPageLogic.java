package testSuites;

//import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.$$;

public class SiteMainPageLogic {

    //String categories = "//a[@class='menu-categories__link']";
    //private By categories = By.xpath("//a[@class='menu-categories__link']");
    private By categories = By.xpath("//a[@class='main-categories__link']");
    //String categories2 = "//a[@class='main-categories__link']";
    //private By notebooksItem = By.xpath("//a[@class='menu__hidden-title']");
    private By notebooksItem = By.xpath("//a[@class='fat-link fat-link_type_popular']");
    //String notebooksItem = "//a[@class='menu__hidden-title']";

    private NotebooksPageLogic notebooksPageLogic;

    public SiteMainPageLogic() {
        String title = "Интернет-магазин ROZETKA™: официальный сайт самого популярного онлайн-гипермаркета в Украине";
        if (!title.equals(Selenide.title())) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public SiteMainPageLogic categoriesCallingClick() {
        $(categories)
                .shouldBe(visible)
                .click();

        /*$$(categories).shouldHave(CollectionCondition.sizeNotEqual(0));
        $(categories)
                .waitUntil(visible, 20)
                .click();*/
        return this;
    }

    public NotebooksPageLogic getNotebooksChapterAndOpenIt() {
        $(notebooksItem)
                .shouldBe(visible)
                .click();
        notebooksPageLogic = new NotebooksPageLogic();
        return notebooksPageLogic;
    }

    public void comparePreBoughtNotebookTitle() throws InterruptedException {
        Notebook selectedNotebook = notebooksPageLogic.getSelectedNotebook();
        Notebook inBasketNotebook = notebooksPageLogic.getInBasketNotebook();
        if (notebooksPageLogic.areDifferentNotebooks(selectedNotebook, inBasketNotebook)) {
            Assert.fail("Наименование товара, добавленного в корзину, не совпадает с товаром на главном экране магазина");
        }
    }
}
