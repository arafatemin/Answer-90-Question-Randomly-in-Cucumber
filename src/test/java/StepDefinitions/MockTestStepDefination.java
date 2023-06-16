package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MockTestStepDefination {

    WebDriver driver = null;

    @Given("user is launching question page")
    public void user_launchs_question_page() {
        driver = new ChromeDriver();
        driver.get("http://uyghuriye.com/#/home)");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("user clicks second MockTest button on home page")
    public void user_clicks_second_mock_test_button_on_home_page() {

        driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(2) > button")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @When("user answers questions randomly")
    public void user_answers_questions_randomly() throws InterruptedException {
        int j = 1;
        for (int i = 1; i <= 120; i++) {

            // tbody tr:nth-of-type(1) button 1 2 3 4
            if (i % 5 == 0 & i != 113) {
//                driver.findElement(By.cssSelector("button#nextButton")).click();
                j = 1;
            } else if (i < 113) {
                WebElement question = driver.findElement(By.cssSelector("tbody tr:nth-of-type(" + j + ") button"));
                question.click();
                if (i < 112) {
                    driver.findElement(By.cssSelector("button#nextButton")).click();
                }
                j += 1;

            }

        }


    }

    @When("user submits exam questions")
    public void user_submits_exam_questions() {
        driver.findElement(By.cssSelector("button#submitButton")).click();
        driver.switchTo().alert().accept();

    }

    @Then("verifies if answers matches on result page")
    public void verifies_if_answers_matches_on_result_page() {
        List<WebElement> corrects = driver.findElements(By.xpath("//div[@id='endQuestionsDiv']/table/tbody//tr/td[2]"));
        List<WebElement> answered = driver.findElements(By.xpath("//div[@id='endQuestionsDiv']/table/tbody//tr/td[3]"));

        Iterator<WebElement> correct = corrects.iterator();
        Iterator<WebElement> answer = answered.iterator();
        while (correct.hasNext() & answer.hasNext()) {
            WebElement q = correct.next();
            WebElement a = answer.next();
            if (q.getText().equals(a.getText())) {
                System.out.println(q.getText() + " ------> " + a.getText() + " green");
            } else {
                System.out.println(q.getText() + " ------> " + a.getText() + " red ");
            }
        }

    }

    @Then("users verifies if  correct answers are green otherwise red")
    public void users_verifies_if_correct_answers_are_green_otherwise_red() {
        List<WebElement> corrects = driver.findElements(By.xpath("//div[@id='endQuestionsDiv']/table/tbody//tr/td[2]"));
        List<WebElement> answered = driver.findElements(By.xpath("//div[@id='endQuestionsDiv']/table/tbody//tr/td[3]"));
        WebElement percentage_size = driver.findElement(By.xpath("/html//div[@id='percentage']"));

        Iterator<WebElement> correct = corrects.iterator();
        Iterator<WebElement> answer = answered.iterator();

        float size = 0.0f;

        while (correct.hasNext() & answer.hasNext()) {
            WebElement c = correct.next();
            WebElement a = answer.next();
            if (c.getText().equals(a.getText())) {
                size += 1; // 10
            }
        }

        float percentage = (100.0f / 90.0f) * size;
        //percentage = (float) Math.ceil(percentage);
        int i = (int) percentage;
        String s = Integer.toString(i);
        s += "%"; // "11.10%"
        if (percentage_size.getText().equals(s)) {
            System.out.println("percentage of browser = " + percentage_size.getText() + " percentage of my " + s);
        } else {
            System.out.println(percentage_size.getText());
            System.out.println(s);
        }

    }

    @Then("user verifies all questions are on the result page")
    public void user_verifies_all_questions_are_on_the_result_page() {
        List<WebElement> corrects = driver.findElements(By.xpath("//div[@id='endQuestionsDiv']/table/tbody//tr/td[2]"));
        System.out.println("there are " + corrects.size() + " question are populated ");

    }

}

