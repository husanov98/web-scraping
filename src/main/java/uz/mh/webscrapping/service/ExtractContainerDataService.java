package uz.mh.webscrapping.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;


import org.springframework.stereotype.Service;
import uz.mh.webscrapping.model.WagonData;


import java.io.IOException;


@Service
public class ExtractContainerDataService {
    public void getDataByNumber(String number) throws IOException {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage("https://id.egov.uz/?client_id=mygove_uz_test&token_id=20fba45b-e25a-4915-a8e9-8044cea7c257&method=IDPW");
        DomNodeList<DomElement> button1 = page.getElementsByTagName("div");
        for (DomElement domElement : button1) {
            System.out.println(domElement.getNodeValue());
        }
        HtmlDivision root = (HtmlDivision) page.getElementById("root");
        System.out.println(root.toString());
        Object button = page.getByXPath("//div[@class='jss122 jss49 jss53']").get(0);
        System.out.println(button.toString());
    }



    private void getPage() throws IOException, ScriptException {
        String url = "https://id.egov.uz/?client_id=mygove_uz_test&token_id=20fba45b-e25a-4915-a8e9-8044cea7c257&method=IDPW";
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
//        HtmlUnitDriver driver =
        webClient.getOptions().setJavaScriptEnabled(true);
//        webClient.getOptions().setCssEnabled(false);
        webClient.waitForBackgroundJavaScript(3000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        HtmlPage page = webClient.getPage(url);
        System.out.println(page.asNormalizedText());
        System.out.println("????????????????????????????????????????????????????????");
//        List<Object> byXPath = page.getByXPath("//button[@class='MuiLoadingButton-root MuiButton-root MuiButton-contained " +
//                "MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium" +
//                " MuiButtonBase-root jss144 css-1yn6idk-MuiButtonBase-root-MuiButton-root-MuiLoadingButton-root']");
//        System.out.println(byXPath.toString());
    }



}
