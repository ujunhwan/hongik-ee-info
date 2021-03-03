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
    }

    @Override
    public boolean login(LoginInfo loginInfo) {
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

        /* login session */
        WebElement element;
        element = driver.findElement(By.xpath(idXpath));
        element.sendKeys(loginInfo.getId());

        element = driver.findElement(By.xpath(pwXpath));
        element.sendKeys(loginInfo.getPw());

        element = driver.findElement(By.xpath(loginButtonXpath));
        element.submit();

        /* alert click */
        while(ExpectedConditions.alertIsPresent().apply(driver) != null) {
            driver.switchTo().alert().accept();
        }

        /* login 성공여부 확인 */
        String currentTitle = driver.getTitle();
        if(currentTitle.equals(classNetTitle)) {
            Map<String, String> Cookies = new HashMap<>();
            Set<Cookie> cookieSet = driver.manage().getCookies();
            driver.quit();
            for(Cookie cookie : cookieSet) {
                Cookies.put(cookie.getName(), cookie.getValue());
            }
            userInfoRepository.saveCookie(Cookies);
            return true;
        }
        driver.quit();
        return false;
    }

    @Override
    public void crawlUserInfo() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
        String userTotalGradeUrl = "https://cn.hongik.ac.kr/stud/P/01000/01000.jsp";
        String userInfoUrl = "https://cn.hongik.ac.kr/stud/A/01000/01000.jsp";

        Document userTotalGradeDoc = null;
        Document userInfoDoc = null;

        if(userInfoRepository.findCookie() == null) {
            return;
        }

        try {
            userTotalGradeDoc = Jsoup.connect(userTotalGradeUrl)
                    .userAgent(userAgent)
                    .cookies(userInfoRepository.findCookie())
                    .timeout(3000)
                    .get();

            userInfoDoc = Jsoup.connect(userInfoUrl)
                    .userAgent(userAgent)
                    .cookies(userInfoRepository.findCookie())
                    .timeout(3000)
                    .get();
        } catch(IOException | NullPointerException e) {
            System.out.println(e);
            return;
        }

        /* 학번 */
        String fullStudentId = userInfoDoc.getElementById("body").selectFirst(".table1").selectFirst("table").selectFirst("tbody").children().first().child(1).text();
        userInfoRepository.saveStudentId(convertStudentId(fullStudentId));

        /* 총 취득학점 */
        Element totalGradeTable = userTotalGradeDoc.getElementById("body").selectFirst(".table1").selectFirst("tbody");
        String acquisition = totalGradeTable.child(0).children().last().children().text();
        userInfoRepository.saveAcquisition(Long.parseLong(acquisition));

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
                    Long fieldId = classInfoRepository.getFieldIdByClass(classId);
                    userInfoRepository.saveTakenClass(classId, fieldId, className);
                }
            }
        }
    }

    @Override
    public List<Long> getClassifiedClasses(Long fieldId) {
        return userInfoRepository.findClassesByField(fieldId);
    }

    private boolean isTakenClass(Elements subjectInfo) {
        return !subjectInfo.last().text().equals("재수강") && !subjectInfo.last().previousElementSibling().text().equals("F");
    }

    private Long convertStudentId(String fullStudentId) {
        Long studentId = 0L;

        if(fullStudentId.charAt(0) == 'B') {
            studentId += 10L;
        } else if(fullStudentId.charAt(0) == 'C') {
            studentId += 20L;
        } else if(fullStudentId.charAt(0) == 'D') {
            studentId += 30L;
        }

        studentId += Long.parseLong(String.valueOf(fullStudentId.charAt(1)));
        return studentId;
    }
}
