package testSuites;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BasketWindowLogic {

    private By preBoughtProduct = By.xpath("//a[@class='cart-product__title']");
    private By closeWindowAction = By.xpath("//button[@class='modal__close']");

    public void BasketWindowLogic() {

    }

    public String getFirstProductTitle() {
        SelenideElement preBoughtNotebookTitle = $(preBoughtProduct);
        String preBoughtNotebookTitleText = preBoughtNotebookTitle.text();
        return preBoughtNotebookTitleText;
    }

    public String getTitleByProduct(SelenideElement product) {
        String productTitleText = product.text();
        return productTitleText;
    }

    public void closeWindow() {
        $(closeWindowAction).shouldBe(visible).click();
    }
}
