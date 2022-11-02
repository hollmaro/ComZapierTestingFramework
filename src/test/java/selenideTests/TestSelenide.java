package selenideTests;

import org.testng.annotations.Test;
import parentTest.ParentTestSelenide;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.testng.Assert.assertEquals;

public class TestSelenide extends ParentTestSelenide{

    @Test
    public void test1() throws InterruptedException {
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        open("https://www.google.com");
        assertEquals(title(), "Google", "Page title is not correct");
        Thread.sleep(15000);
    }
}
