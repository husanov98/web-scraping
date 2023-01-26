package uz.mh.webscrapping.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.mh.webscrapping.model.OrganizationData;
import uz.mh.webscrapping.model.OrganizationDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ExtractDataOzInfoService {

    public void getPhoneNumbers(MultipartFile file) throws IOException {
        List<String> organizations = readFromExcel(file);
        int rowId = 0;
        String excelFile = "Organization.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Organizations");
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        for (String organization : organizations) {
            if (organization != null) {
                OrganizationData organizationData = getOrganizationData(client, organization);

                if (rowId == 0) {
                    writeToFirstRow(organizationData.getDefinitionTerms(), excelFile, workbook, sheet, rowId);
                } else {
                    writeExcel(organizationData.getDescriptions(), rowId, workbook, sheet, excelFile);
                }
                System.out.println(rowId);
                rowId++;
            }
        }
        FileOutputStream file1 = new FileOutputStream(excelFile);
        workbook.write(file1);
        file1.flush();
        file1.close();
    }

    private OrganizationData getOrganizationData(WebClient client, String organization) throws IOException {
        OrganizationData organizationData = new OrganizationData();
        List<String> definitionTerms = new ArrayList<>();
        List<String> definitionDescriptions = new ArrayList<>();
        String url = "https://orginfo.uz/search?q=" + organization;
        try {
            HtmlPage page = client.getPage(url);
            HtmlAnchor searchedOrganization = (HtmlAnchor) page.getByXPath("//a[@class='organization-page-link']").get(0);
            HtmlPage resultPage = searchedOrganization.click();

            HtmlDefinitionList dataLists = (HtmlDefinitionList) resultPage.getByXPath("//dl[@class='row organization-detail']").get(0);


            List<HtmlDefinitionTerm> definitionTermList = dataLists.getByXPath("//dt");
            List<HtmlDefinitionDescription> definitions = dataLists.getByXPath("//dd");
            for (HtmlDefinitionTerm definitionTerm : definitionTermList) {
                definitionTerms.add(definitionTerm.getTextContent());
            }
            for (HtmlDefinitionDescription definition : definitions) {
                definitionDescriptions.add(definition.asNormalizedText());
            }
            organizationData.setDescriptions(definitionDescriptions);
            organizationData.setDefinitionTerms(definitionTerms);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return organizationData;
    }

    private void writeExcel(List<String> definitionDescriptions, int rowId, XSSFWorkbook workbook, XSSFSheet sheet, String excelFile) {
        try {
            int cellCount = 0;

            XSSFRow row1 = sheet.createRow(rowId);
            for (String definitionDescription : definitionDescriptions) {

                Cell cell = row1.createCell(cellCount);
                cell.setCellValue(definitionDescription);
                cellCount++;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private List<String> readFromExcel(MultipartFile file) {
        List<String> organizations = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Iterator<Cell> cellsInRow = currentRow.cellIterator();
                Cell cellInRow = currentRow.getCell(6);
                String organization = cellInRow.getStringCellValue();
                if (organization.equals("")) {
                    continue;
                }
                if (organization.equals("Yuk jo‘natuvchining nomi")){
                    continue;
                }
                organizations.add(extractBetweenTwoQuotes(organization));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return organizations;
    }

    private void writeToFirstRow(List<String> definitionTerms, String excelFile, XSSFWorkbook
            workbook, XSSFSheet sheet, int rowId) {

        XSSFRow row = sheet.createRow(rowId);
        int cellId = 0;
        try {


            for (String definitionTerm : definitionTerms) {

                if (definitionTerm.startsWith("\n")) {
                    continue;
                }
                Cell cell = row.createCell(cellId);
                cell.setCellValue(definitionTerm);
                cellId++;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private String extractBetweenTwoQuotes(String organization) {
        int begIndex = 0;
        int endIndex = 0;
        int s = 1;
        for (int i = 0; i < organization.length(); i++) {
            if (s == 1) {
                if (organization.charAt(i) == '"') {
                    begIndex = i;
                    s++;
                }
            }else if (organization.charAt(i) == '"'){
                endIndex = i;
                return organization.substring(begIndex,endIndex);
            }
        }
        return null;
    }

}
