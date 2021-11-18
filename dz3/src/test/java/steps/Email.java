package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

public class Email {
    @Given("i am located on the main page")
    public void openedBrowser() {
        assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://yandex.ru/");
    }

    @And("go to the passport")
    public void clickPassport() {
        $(By.xpath("//span[text()='t']/..")).click();
        $(By.xpath("//span[text()='Управление аккаунтом']/..")).click();
        // "//div[text()='Почта']" //
    }

    @And("go to the mail")
    public void clickMail(){
        $(By.xpath("//span[text()='testselenid2']/..")).click();
        $(By.xpath("//span[text()='Почта']/..")).click();
    }

    @And("go to my services")
    public void clickMyServices(){
        $(By.xpath("//span[text()='testselenid2']/..")).click();
        $(By.xpath("//span[text()='Управление аккаунтом']/..")).click();
        $(By.xpath("//span[text()='Мои сервисы']/..")).click();
    }

    @And("the user will switch to disk")
    public void clickDisk() {
        $(By.xpath("//a[text()='Диск']")).click();
    }

    @Then("go to the yandex disk page")
    public void check(){
        $(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div[3]/div/div/a[1]/div")).click();
        $(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div[3]/div/div/div/ul/div[1]/div/span")).shouldHave(text("testselenid2"));
    }

}
