package gimkitBot;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Classic extends BasicFunctions{
    private ChromeDriver driver;

    public Classic(String id, String name) {
        super(id, name);
        driver = getDriver();
    }

    public void start() throws InterruptedException {
        super.log_in();

        if (!driver.findElements(By.xpath("//span[@class='anticon anticon-close-circle']")).isEmpty()) {return;}

        startBot();
    }

    private void startBot() {
        while (true) {
            try {
                if (!driver.findElements(By.xpath("//span[@class='notranslate lang-en']")).isEmpty()) {
                    multipleChoice();
                }
                else if (!driver.findElements(By.xpath("//div[@name='Continue']")).isEmpty()) {
                    learn();
                }
            } catch (NoSuchElementException e) {}
        }
    }
    public void stop() {
        driver.quit();
    }
}
