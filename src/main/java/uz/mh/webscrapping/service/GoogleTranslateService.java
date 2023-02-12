//package uz.mh.webscrapping.service;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class GoogleTranslateService {
//
//    private static String TRANSLATE_URL = "https://translate.google.com/?hl=uz&sl=en&tl=ru&op=translate";
//    private static final String WORD_SOURCE_ID = "source";
//    private static final String TRANSLATION_SELECTOR = "VfPpkd-Bz112c-RLmnJb";
//    private static final int WORD_TRANSLATION_WAIT_TIMEOUT = 1000;
//    private static final int SERVICE_LOADING_TIMEOUT = 1000;
//    private static final GoogleTranslateService INSTANCE = new GoogleTranslateService();
//    private PhantomJSDriver driver;
//
//    private GoogleTranslateService() {
//        System.setProperty("phantomjs.binary.path", "/usr/local/bin/phantomjs");
//        String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
//        System.setProperty("phantomjs.page.settings.userAgent", userAgent);
//        this.driver = new PhantomJSDriver();
//    }
//
//    public static GoogleTranslateService getInstance() {
//        return INSTANCE;
//    }
//
//    public List<String> translateWords(List<String> words, String from, String to) {
//
//        List<String> translations = new ArrayList<>(words.size());
//        driver.get(String.format(TRANSLATE_URL, from, to));
//        waitInterval(SERVICE_LOADING_TIMEOUT);
//        for (String word : words) {
//            driver.findElement(By.tagName("textarea")).sendKeys(word);
//            waitInterval(WORD_TRANSLATION_WAIT_TIMEOUT);
//            driver.findElement(By.tagName("textarea")).clear();
//            WebElement translationElement = driver.findElement(By.className("VfPpkd-Bz112c-RLmnJb"));
//
//            if (translationElement != null) {
//                translations.add(translationElement.getText());
//            }
//        }
//        return translations;
//    }
//
//    private void waitInterval(long timeInMs) {
//        try {
//            Thread.sleep(timeInMs);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
