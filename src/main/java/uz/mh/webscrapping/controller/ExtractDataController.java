package uz.mh.webscrapping.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.mh.webscrapping.service.ExtractContainerDataService;
import uz.mh.webscrapping.service.ExtractDataOzInfoService;
import uz.mh.webscrapping.service.ExtractNumberService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

//import static org.springframework.core.env.ProfilesParser.not;

@RestController
public class ExtractDataController {
    private final ExtractNumberService numberService;
    private final ExtractDataOzInfoService infoService;
    private final ExtractContainerDataService containerDataService;

    public ExtractDataController(ExtractNumberService numberService, ExtractDataOzInfoService infoService, ExtractContainerDataService containerDataService) {
        this.numberService = numberService;
        this.infoService = infoService;
        this.containerDataService = containerDataService;
    }


    @PostMapping(value = "/getTruckData", consumes = {"multipart/form-data"})

    public ResponseEntity getSomething(@RequestPart(name = "file") MultipartFile file, HttpServletResponse response) throws IOException {
        XSSFWorkbook dataByNumber = numberService.getDataByNumber(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        dataByNumber.write(bos);
        bos.close();

        return ResponseEntity.ok().body(bos.toByteArray());
    }

    @PostMapping(value = "/getPhoneNumbers", consumes = {"multipart/form-data"})
    public void getPhoneNumbers(@RequestPart(name = "file") MultipartFile file) throws IOException {
        infoService.getPhoneNumbers(file);
    }

    @GetMapping("/getWagonData")
    public void getWagonData(@RequestParam(name = "wagonNumber") String wagonNumber) throws IOException {
        containerDataService.getDataByNumber(wagonNumber);
    }
}
