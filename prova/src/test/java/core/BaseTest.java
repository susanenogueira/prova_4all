package core;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.sun.deploy.cache.Cache.copyFile;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import core.Generator;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();

    @Before
    public void inicializa() {
        getDriver().get("https://shopcart-challenge.4all.com/");
    }

    @After
    public void finaliza() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        copyFile(arquivo, new File("src" + File.separator +"test" + File.separator +"java" + File.separator + "screenshot" + File.separator + testName.getMethodName()+"_" + Generator.dataHoraParaArquivo() +".jpg"));

        if (Propriedades.FECHAR_BROWSER) {
            killDriver();

        }
    }
}
