//package uz.mh.webscrapping.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import uz.mh.webscrapping.service.GoogleTranslateService;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//public class GoogleTranslateController {
//
//    private final GoogleTranslateService translateService = GoogleTranslateService.getInstance();
//
//    @PostMapping(value = "/translate")
//    public void translate() throws IOException {
//
//        List<String> strings = translateService.translateWords(Arrays.asList("dog", "cat"), "English", "Uzbek");
//        System.out.println(strings);
//    }
//}
