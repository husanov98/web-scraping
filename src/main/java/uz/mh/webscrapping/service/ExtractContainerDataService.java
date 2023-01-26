package uz.mh.webscrapping.service;

import com.gargoylesoftware.htmlunit.Page;
import javafx.application.Application;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Service;
import uz.mh.webscrapping.model.WagonData;

import java.io.IOException;
import java.util.List;

@Service
public class ExtractContainerDataService extends Application {
    public void getDataByNumber(String number) throws IOException {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage("https://my.gov.uz/oz/gtk-rail/passport");
//        System.out.println(page);
//        System.out.println(page.getContentType());


//        getWagonData(number, client);
//        HtmlDivision division = (HtmlDivision) page.getByXPath("//div[@class='right-side desktopView']").get(0);
//        System.out.println(division.asNormalizedText());
        HtmlAnchor kirish = page.getAnchorByText("Kirish");
        Page click = kirish.click();
        System.out.println(click.toString());
        HtmlPage loginPage = kirish.click();
//        System.out.println(loginPage.asNormalizedText());
        List<Object> byXPath = loginPage.getByXPath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary " +
                "MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-fullWidth MuiButtonBase-root" +
                " jss57 css-plwtfs-MuiButtonBase-root-MuiButton-root']");
//        System.out.println(byXPath.toString());
//        System.out.println(loginPage.getDocumentElement());
        System.out.println(byXPath.toString());


    }

    private WagonData getWagonData(String number, WebClient client) throws IOException {
        String url = "https://my.gov.uz/oz/gtk-rail";
        HtmlPage page = client.getPage(url);
        HtmlForm form = (HtmlForm) page.getByXPath("//form").get(0);
//        loginToSystem(page);
//        System.out.println(page.asNormalizedText());
//        System.out.println(form.asNormalizedText());

        HtmlInput inputWagonType = form.getInputByName("GtkRail[wcflag]");
        HtmlInput inputWagonNumber = form.getInputByName("GtkRail[number]");
        inputWagonType.type("Vagon");
        inputWagonNumber.type(number);
        HtmlButton button = (HtmlButton) form.getByXPath("//button").get(0);
        HtmlPage resultPage = button.click();
//        String onKeyPressAttribute = inputWagonType.getOnKeyPressAttribute();
//        System.out.println(onKeyPressAttribute.toString());
//        inputWagonNumber.type(number);
        System.out.println(resultPage.asNormalizedText());
        return null;
    }

    private void loginToSystem(HtmlPage page) throws IOException {

        for (HtmlAnchor anchor : page.getAnchors()) {
            System.out.println(anchor.asNormalizedText());
        }

        HtmlAnchor anchorByText = page.getAnchorByText("/oz/auth/login?redirect_url=%2Foz%Fgtk-rail%2Fpassport");
        HtmlAnchor login = page.getAnchorByHref("/oz/auth/login?redirect_url=%2Foz%Fgtk-rail%2Fpassport");
        System.out.println(login.getPage().asNormalizedText());
        HtmlPage loginPage = login.click();
        System.out.println(loginPage.asNormalizedText());
    }

    @Override
    public void start(Stage stage) throws Exception {
//        JSObject jsObject = getHostServices().
    }
}
