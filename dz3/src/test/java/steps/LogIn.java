package steps;

import LoginPassword.Source;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class LogIn {


    @Given("i am on yandex")
    public void openUrl(){
        open("https://yandex.ru");
    }

    @When("the authorization page is open")
    public void clickAuth() {
        $(By.xpath("//div[text()='Войти']")).click();
    }

    @And("login entered")
    public void inputLogin() {
        $(By.id("passp-field-login")).val(Source.email).pressEnter();
    }


    @And("password entered")
    public void inputPassword() {
        $(By.id("passp-field-passwd")).val(Source.pasword).pressEnter();
        sleep(1000);
    }

    @Then("i am see the yandex home page")
    public void check() {
        $(By.className("username__first-letter")).shouldHave(text("t"));

        given().baseUri("https://api.passport.yandex.ru")
                .when().get("/all_accounts")
                .then().statusCode(200)
                .extract().jsonPath();
    }
}
