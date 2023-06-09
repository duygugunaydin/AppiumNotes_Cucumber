package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ReusableMethods {

    static int waitMillis= 100;
    static TouchAction action=new TouchAction<>(BrowserDriver.getBrowserDriver());

    public static void multipleClick(int numberOfClick,int xCoordinat,int yCoordinat){

        for (int i=0; i<numberOfClick; i++){
            action
                    .press(PointOption.point(xCoordinat,yCoordinat))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(waitMillis)))
                    .release()
                    .perform();
        }
    }
    public static void clickToCoordinat(int xCoordinat,int yCoordinat){

        action
                .press(PointOption.point(xCoordinat,yCoordinat))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(waitMillis)))
                .release()
                .perform();

    }
    public static void scroll(int startCoordinatX,int startCoordinatY,int finishCoordinatX,int finishCoordinatY) {

        action
                .press(PointOption.point(startCoordinatX, startCoordinatY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(waitMillis)))
                .moveTo(PointOption.point(finishCoordinatX, finishCoordinatY))
                .release()
                .perform();

    }

    public static void scrollWithUiScrollable(String elementText) {
        AndroidDriver driver = (AndroidDriver) BrowserDriver.getBrowserDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))");
        driver.findElementByXPath("//*[@text='" + elementText + "']").click();

    }
    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) BrowserDriver.getBrowserDriver();

        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }
    public static void wait(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
