package testSuites;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NotebooksPageLogic {

    private By firstNotebookPageElem = By.xpath("//a[@class='goods-tile__heading']");
    private By buyingBtn = By.xpath("//button[contains(@class, 'buy-button goods-tile__buy-button')]");
    private By preBoughtCounterBtn = By.xpath("//span[@class='header-actions__button-counter']");

    private BasketWindowLogic basketWindow;

    private Notebook selectedNotebook;
    private Notebook inBasketNotebook;

    public NotebooksPageLogic() {

    }

    private Notebook setNotebookTitle(Notebook notebook, String notebookTitle) {
        if (notebook == null) {
            notebook = new Notebook(notebookTitle);
        } else {
            notebook.setTitle(notebookTitle);
        }
        return notebook;
    }

    public NotebooksPageLogic findFirstNotebook() {
        SelenideElement firstNotebookSE = $(firstNotebookPageElem)
                .shouldBe(visible);
        String notebookTitle = firstNotebookSE.attr("innerText");
        selectedNotebook = setNotebookTitle(selectedNotebook, notebookTitle);
        return this;
    }

    public Notebook getSelectedNotebook() {
        return selectedNotebook;
    }

    public Notebook getInBasketNotebook() {
        if (inBasketNotebook == null) {
            showBasketWindow();
            if (basketWindow != null) {
                basketWindow.closeWindow();
            }
        }
        return inBasketNotebook;
    }

    public NotebooksPageLogic buySelectedNotebook() {
        $(buyingBtn)
                .shouldBe(visible)
                .click();
        return this;
    }

    private int getPreBoughtProductsCounter() {
        SelenideElement counter = $(preBoughtCounterBtn);
        String counterAsStr = counter.text();
        int productsNumber = Integer.parseInt(counterAsStr);
        return productsNumber;
    }

    private BasketWindowLogic showBasketWindow() {

        SelenideElement counter = $(preBoughtCounterBtn);
        counter.shouldHave(text("1")).click();

        if (basketWindow == null)
            basketWindow = new BasketWindowLogic();
        String inBasketNotebookTitle = basketWindow.getFirstProductTitle();
        inBasketNotebook = setNotebookTitle(inBasketNotebook, inBasketNotebookTitle);

        return basketWindow;
    }

    public boolean areDifferentNotebooks(Notebook notebook1, Notebook notebook2) {
        String onGridProductTitle = notebook1.getTitle();
        int productsCounter = getPreBoughtProductsCounter();
        if (productsCounter == 0) {
            Assert.fail("В корзину не было добавлено ни одного экземпляра товара");
        } else if (productsCounter > 1) {
            Assert.fail("В корзину было добавлено больше 1 экземпляра товара");
        } else {
            showBasketWindow();
            String inBasketProductTitle = notebook2.getTitle();
            if (!onGridProductTitle.equals(inBasketProductTitle)) {
                return true;
            }
        }
        return false;
    }
}
