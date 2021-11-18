package steps;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.xlstest.XLS;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class Disk {
    @Given("i am on the disk")
    public void openedBrowser() {
        assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://disk.yandex.ru/client/disk");
    }

    @And("selected file")
    public void clickPhoto() {
        $(By.xpath("//span[text()='test.xls']/../..")).click();
    }

    @And ("the download button is pressed")
    public void clickDownload() {
        $(By.xpath("//span[text()='Скачать']/..")).click();
        sleep(10000);
    }


    @And ("load image on disk")
    public void uploadFile() {
        refresh();
        File imgFile = new File("uther.jpg");
        $(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[1]/span[1]/span[2]/div[1]/input[1]")).uploadFile(imgFile);
        sleep(10000);
    }

    @Then("check the file")
    public void checkFile(){
        File file = searchFileByDeepness("D:/jaba/TestiPO1/dz3","test.xls");

        XLS xls = new XLS(file);
        assertThat(xls, XLS.containsText("test1"));
        assertThat(xls, XLS.containsText("test2"));
        assertThat(xls, XLS.containsText("test3"));
        assertThat(xls, XLS.containsText("test4"));
        sleep(1000);
    }

    //поиск файла в деректории
    public static File searchFileByDeepness(final String directoryName, final String fileName)
    {
        File target = null;
        if(directoryName != null && fileName != null) {
            File directory = new File(directoryName);
            if(directory.isDirectory()) {
                File file = new File(directoryName, fileName);
                if(file.isFile()) {
                    target = file;
                }
                else {
                    List<File> subDirectories = getSubDirectories(directory);
                    do {
                        List<File> subSubDirectories = new ArrayList<File>();
                        for(File subDirectory : subDirectories) {
                            File fileInSubDirectory = new File(subDirectory, fileName);
                            if(fileInSubDirectory.isFile()) {
                                return fileInSubDirectory;
                            }
                            subSubDirectories.addAll(getSubDirectories(subDirectory));
                        }
                        subDirectories = subSubDirectories;
                    } while(subDirectories != null && ! subDirectories.isEmpty());
                }
            }
        }

        return target;
    }

    private static List<File> getSubDirectories(final File directory) {
        File[] subDirectories = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File current, final String name) {
                return new File(current, name).isDirectory();
            }
        });
        return Arrays.asList(subDirectories);
    }



}
