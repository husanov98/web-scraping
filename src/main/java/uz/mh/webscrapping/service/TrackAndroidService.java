//package uz.mh.webscrapping.service;
//
//
//import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.time.Duration;
//import java.util.ArrayList;
//
//@Service
//public class TrackAndroidService {
//    private static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
//    private static DesiredCapabilities desiredCapabilities;
//    private  WebDriver driver;
//
//    public void initPhantomJs() {
//        desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setJavascriptEnabled(true);
//        desiredCapabilities.setCapability("takesScreenshot", false);
//        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/local/bin/phantomjs");
//        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", USER_AGENT);
//
//        ArrayList<String> cliArgsCap = new ArrayList();
//        cliArgsCap.add("--web-security=false");
//        cliArgsCap.add("--ssl-protocol=any");
//        cliArgsCap.add("--ignore-ssl-errors=true");
//        cliArgsCap.add("--webdriver-loglevel=ERROR");
//
//        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
//        driver = new PhantomJSDriver(desiredCapabilities);
//        driver.manage().window().setSize(new Dimension(1920, 1080));
//    }
//
//    public void get() throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
//        System.setProperty("phantomjs.page.settings.userAgent", USER_AGENT);
//        String baseUrl = "https://www.inshorts.com/en/read";
//        initPhantomJs();
//        driver.get(baseUrl);
//        int nbArticlesBefore = driver.findElements(By.xpath("//div[@class='card-stack']/div")).size();
//        driver.findElement(By.id("load-more-btn")).click();
//        Duration duration = Duration.ofSeconds(30);
//
////        WebDriverWait wait = new WebDriverWait(driver,duration );
//        // We wait for the ajax call to fire and to load the response into the page
//        Thread.sleep(800);
//        int nbArticlesAfter = driver.findElements(By.xpath("//div[@class='card-stack']/div")).size();
//        System.out.println(String.format("Initial articles : %s Articles after clicking : %s", nbArticlesBefore, nbArticlesAfter));
//    }
//}
