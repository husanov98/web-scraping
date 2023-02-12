//package uz.mh.webscrapping.controller;
//
//import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import uz.mh.webscrapping.service.TrackAndroidService;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//
//@RestController
//public class TrackAndroidController {
//    private final TrackAndroidService androidService;
//
//    public TrackAndroidController(TrackAndroidService androidService) {
//        this.androidService = androidService;
//    }
//
//    @PostMapping(value = "/getShorts")
//    public void seePhantomJs()throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
//        androidService.get();
//    }
//}
