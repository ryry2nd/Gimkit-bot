package gimkitBot;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.InvalidSelectorException;

public class Classic extends BasicFunctions{
    private ChromeDriver driver;

    public Classic(String id, String name) {super(id, name);driver = getDriver();}

    public void start() throws InterruptedException {
        super.log_in();
        if (!driver.findElements(By.xpath("//span[@class='anticon anticon-close-circle']")).isEmpty()) {return;}
        startBot();
    }

    private void startBot() throws InterruptedException {
        while (true) {try {
            if (!driver.findElements(By.xpath("//div[@style='height: 100%; width: 100%; display: flex; justify-content: center; align-items: center; padding: 7px 6%; box-sizing: border-box; font-weight: 900; font-size: 25px;']")).isEmpty()) {
                multipleChoice();
                Thread.sleep(1000);}
            else if (!driver.findElements(By.xpath("//div[@style='height: 100%; width: 100%; display: flex; justify-content: center; align-items: center; padding: 15px 6%; box-sizing: border-box; font-weight: 900; font-size: 40px;']")).isEmpty()) {
                learn();
            }} catch(InvalidSelectorException e) {}}}
    public void stop() {driver.quit();}
}
