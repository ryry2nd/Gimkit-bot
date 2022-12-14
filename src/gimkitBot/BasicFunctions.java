package gimkitBot;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class BasicFunctions {
    private String id, name, prevQuestion;
    private ChromeDriver driver;
    private static Map<String, String> words;

    public BasicFunctions(String id, String name) {
        this.id = id;
        this.name = name;
        words = new HashMap<String, String>();

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--allowed-ips");
        driver = new ChromeDriver(options);
    }

    public ChromeDriver getDriver() {return driver;}

    public void log_in() throws InterruptedException {
        driver.get("https://gimkit.com/join");
        Thread.sleep(500);

        WebElement idBox = driver.findElement(By.xpath("//input[@class='sc-iIHjhz gODgKf']"));
        idBox.sendKeys(id);
        idBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        WebElement nBox = driver.findElement(By.xpath("//input[@class='sc-iIHjhz gODgKf']"));
        nBox.sendKeys(name);
        nBox.sendKeys(Keys.ENTER);
        
        //Thread.sleep(2000);
    }

    public String findAns(String question) {
        for (String key : words.keySet()) {if (key.equals(question)) {return words.get(key);}}
        return null;
    }

    public void multipleChoice() {
        WebElement pAns; String cAns; int i;
        List<WebElement> preQAA = driver.findElements(By.xpath("//span[@class='notranslate lang-en']"));

        prevQuestion = preQAA.get(0).getText();
        cAns = findAns(prevQuestion);

        if (cAns == null) {preQAA.get(1).click();}
        else {for (i = 0; i < preQAA.size() - 1; i++) {
                pAns = preQAA.get(i + 1);
                if (pAns.getText().equals(cAns)) {pAns.click();}}
            driver.findElement(By.xpath("//div[@class='sc-kOnlKp dIDLaR']")).click();
        }
    }

    public void learn() {
        List<WebElement> preCorrectAns = driver.findElements(By.xpath("//span[@class='notranslate lang-en']"));
        String correctAns;

        if (!preCorrectAns.isEmpty()){
            correctAns = preCorrectAns.get(0).getText();
            words.put(prevQuestion, correctAns);
        }
        pressEnter();
    }

    public void pressEnter() {driver.findElement(By.xpath("//html")).sendKeys(Keys.ENTER);}
}