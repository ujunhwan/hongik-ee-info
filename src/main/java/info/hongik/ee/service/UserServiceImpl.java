package info.hongik.ee.service;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.repository.ClassInfoRepository;
import info.hongik.ee.repository.UserInfoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.*;

public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;
    private final ClassInfoRepository classInfoRepository;

    public UserServiceImpl(UserInfoRepository userInfoRepository, ClassInfoRepository classInfoRepository) {
        this.userInfoRepository = userInfoRepository;
        this.classInfoRepository = classInfoRepository;
        setDriver();
    }

    @Override
    public boolean login(LoginInfo loginInfo) {
        String classNetTitle = "학생 클래스넷";

        /* 로그인 페이지 input XPath */
        String idXpath = "//*[@id=\"main\"]/div[2]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/form/div/div/div[1]/div[1]/input";
        String pwXpath = "//*[@id=\"main\"]/div[2]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/form/div/div/div[1]/div[2]/input";
        String loginButtonXpath = "//*[@id=\"main\"]/div[2]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/form/div/div/div[2]/button";

        WebDriver driver = userInfoRepository.getDriver();

        /* login session */
        WebElement element;
        element = driver.findElement(By.xpath(idXpath));
        element.sendKeys(loginInfo.getId());

        element = driver.findElement(By.xpath(pwXpath));
        element.sendKeys(loginInfo.getPw());

        element = driver.findElement(By.xpath(loginButtonXpath));
        element.submit();

        /* alert click */
        if(ExpectedConditions.alertIsPresent().apply(driver) != null) {
            driver.switchTo().alert().accept();
        }

        /* login 성공여부 확인 */
        String currentTitle = driver.getTitle();
        if(currentTitle.equals(classNetTitle)) {
            Map<String, String> cookies = new HashMap<>();
            Set<Cookie> cookieSet = driver.manage().getCookies();
            driver.quit();
            for(Cookie cookie : cookieSet) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
            userInfoRepository.saveUserId(loginInfo.getId());
            userInfoRepository.saveStudentId(convertStudentId(loginInfo.getId()));
            userInfoRepository.saveCookie(cookies);
            return true;
        }
        driver.quit();
        return false;
    }

    @Override
    public boolean logout() {
        setDriver();
        return true;
    }

    @Override
    public void crawlUserInfo() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
        String userTotalGradeUrl = "https://cn.hongik.ac.kr/stud/P/01000/01000.jsp";

        Document userTotalGradeDoc = null;

        if(userInfoRepository.getCookie() == null) {
            return;
        }

        try {
            userTotalGradeDoc = Jsoup.connect(userTotalGradeUrl)
                    .userAgent(userAgent)
                    .cookies(userInfoRepository.getCookie())
                    .timeout(3000)
                    .get();

        } catch(IOException | NullPointerException e) {
            System.out.println(e);
            return;
        }

        /* 총 취득학점 */
        Element totalGradeTable = userTotalGradeDoc.getElementById("body").selectFirst(".table1").selectFirst("tbody");
        String acquisition = totalGradeTable.child(0).children().last().children().text();
        userInfoRepository.saveAcquisition(acquisition);

        /* 전체 수강 과목 */
        Map<Long, String> takenClasses = new HashMap<>();
        Elements semesters = userTotalGradeDoc.getElementById("body").select(".table0");
        for(Element gradeTable : semesters) {
            Elements rows = gradeTable.selectFirst("tbody").children();
            for(Element row : rows) {
                Elements classInfo = row.children();
                if(row.nextElementSibling() == null) continue;
                if(isTakenClass(classInfo)) {
                    Long classId = Long.parseLong(classInfo.first().text());
                    String className = classInfo.first().nextElementSibling().text();
                    System.out.println(classId + " " + className);
//                    Long fieldId = classInfoRepository.getFieldIdByClass(classId);
//                    userInfoRepository.saveTakenClass(classId, fieldId, className);
                }
            }
        }
    }

    @Override
    public List<Long> getClassifiedClasses(Long fieldId) {
        return null;
    }

    private boolean isTakenClass(Elements subjectInfo) {
        return !subjectInfo.last().text().equals("재수강") && !subjectInfo.last().previousElementSibling().text().equals("F");
    }

    private String convertStudentId(String fullStudentId) {
        int studentId = 0;
        char firstDigit = fullStudentId.charAt(0);
        char secondDigit = fullStudentId.charAt(1);

        if (firstDigit == 'b' || firstDigit == 'B') {
            studentId += 10;
        } else if(firstDigit == 'c' || firstDigit == 'C') {
            studentId += 20;
        } else if(firstDigit == 'd' || firstDigit == 'D') {
            studentId += 30;
        }

        studentId += secondDigit - 48;
        return Integer.toString(studentId);
    }

    private void setDriver() {
        String loginPageUrl = "http://www.hongik.ac.kr/login.do?Refer=https://cn.hongik.ac.kr/";
        System.setProperty("webdriver.chrome.driver", "/Users/junhwan/projects/hongik-ee-info/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(loginPageUrl);
        userInfoRepository.saveDriver(driver);
    }
}
