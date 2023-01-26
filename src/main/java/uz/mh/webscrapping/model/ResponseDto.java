package uz.mh.webscrapping.model;

import lombok.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDto {
    private XSSFWorkbook workbook;
}
