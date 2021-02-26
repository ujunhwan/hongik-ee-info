package info.hongik.ee.service;


import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.domain.User;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserServiceImpl implements UserService {
    @Override
    public boolean userLogin(LoginInfo loginInfo) {
        String loginPageUrl = "http://www.hongik.ac.kr/login.do?Refer=https://cn.hongik.ac.kr/";
        String classNetTitle = "학생 클래스넷";

        /* 로그인 페이지 input XPath */
        String idXpath = "//*[@id=\"main\"]/div[2]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/form/div/div/div[1]/div[1]/input";
        String pwXpath = "//*[@id=\"main\"]/div[2]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/form/div/div/div[1]/div[2]/input";
        String loginButtonXpath = "//*[@id=\"main\"]/div[2]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/form/div/div/div[2]/button";

        /* server에 올릴때 경로 변경해야됨 */
        System.setProperty("webdriver.chrome.driver", "/Users/junhwan/projects/hongik-ee-info/chromedriver");

        /* selenium driver configuration */
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(loginPageUrl);

        /* login session 얻는 과정 */
        WebElement element;
        element = driver.findElement(By.xpath(idXpath));
        element.sendKeys(loginInfo.getId());

        element = driver.findElement(By.xpath(pwXpath));
        element.sendKeys(loginInfo.getPw());

        element = driver.findElement(By.xpath(loginButtonXpath));
        element.submit();

        // submit 있다면 클릭
        if(ExpectedConditions.alertIsPresent().apply(driver) != null) {
            driver.switchTo().alert().accept();
        }
        String currentTitle = driver.getTitle();

        if(currentTitle.equals(classNetTitle)) {
            Map<String, String> cookies = new HashMap<>();
            Set<Cookie> cookieSet = driver.manage().getCookies();
            for(Cookie cookie : cookieSet) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
            driver.quit();
            scrapeUserInfo(cookies);
            return true;
        }
        driver.quit();
        return false;
    }

    @Override
    public void scrapeUserInfo(Map<String, String> cookies) {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
        String userTotalGradeUrl = "https://cn.hongik.ac.kr/stud/P/01000/01000.jsp";

        Document userTotalGradeDoc = null;
        try {
            userTotalGradeDoc = Jsoup.connect(userTotalGradeUrl)
                    .userAgent(userAgent)
                    .cookies(cookies)
                    .timeout(3000)
                    .get();
        } catch(IOException | NullPointerException e) {
            System.out.println(e);
            return;
        }

        // todo: parsing
        Element totalGradeBody = userTotalGradeDoc.getElementById("body");
        System.out.println(totalGradeBody.text());
    }

    @Override
    public HashMap<String, Long> classifySubjects(HashSet<Long> subjects) {
        HashMap<String, Long> classified = new HashMap<>();
        HashMap<Long, String> subjectCodeByField = getSubjectCodeByField();

        for(Long subject : subjects) {
            classified.put(subjectCodeByField.get(subject), subject);
        }

        return classified;
    }

    public HashMap<Long, String> getSubjectCodeByField() {
        // DB에서 가져옴
        // 학수번호, 분야
        HashMap<Long, String> subjectCode = new HashMap<>();
        return subjectCode;
    }
}
