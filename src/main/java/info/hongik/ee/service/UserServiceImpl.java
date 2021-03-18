package info.hongik.ee.service;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.repository.ClassInfoRepository;
import info.hongik.ee.repository.UserInfoRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;
    private final ClassInfoRepository classInfoRepository;

    Map<String, String> headers = Map.of("content-type", "application/x-www-form-urlencoded");
    String loginPageUrl = "https://ap.hongik.ac.kr/login/LoginExec3.php";
    String cnUrl = "https://cn.hongik.ac.kr";
    String cnMainUrl = "https://cn.hongik.ac.kr/stud";
    String gradeUrl = "https://cn.hongik.ac.kr/stud/P/01000/01000.jsp";
    String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36";

    public UserServiceImpl(UserInfoRepository userInfoRepository, ClassInfoRepository classInfoRepository) {
        this.userInfoRepository = userInfoRepository;
        this.classInfoRepository = classInfoRepository;
    }

    @Override
    public boolean login(LoginInfo loginInfo) {
        Map<String, String> cookies = getLoginCookie(loginInfo);
        cookies.putAll(getCookie(cnUrl, cookies));
        cookies.putAll(getCookie(cnMainUrl, cookies));
        crawlUserInfo(cookies);
        // 임시
        return true;
    }

    @Override
    public boolean logout() {
        // 임시
        return true;
    }

    @Override
    public void crawlUserInfo(Map<String, String> cookies) {
        Document gradeDoc;
        try {
            gradeDoc = Jsoup.connect(gradeUrl)
                    .userAgent(userAgent)
                    .cookies(cookies)
                    .timeout(3000)
                    .get();
        } catch(IOException | NullPointerException e) {
            System.out.println(e);
            return;
        }

        /* 총 취득학점 */
        Element totalGradeTable = gradeDoc.getElementById("body").selectFirst(".table1").selectFirst("tbody");
        String acquisition = totalGradeTable.child(0).children().last().children().text();
        userInfoRepository.saveAcquisition(acquisition);

        /* 전체 수강 과목 */
        Map<Long, String> takenClasses = new HashMap<>();
        Elements semesters = gradeDoc.getElementById("body").select(".table0");
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

    private Map<String, String> getLoginCookie(LoginInfo loginInfo) {
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> loginData = new HashMap<>();
        loginData.put("USER_ID", loginInfo.getId());
        loginData.put("PASSWD", loginInfo.getPw());

        Document loginPage = null;
        try {
            loginPage = Jsoup.connect(loginPageUrl)
                    .timeout(5000)
                    .userAgent(userAgent)
                    .headers(headers)
                    .data(loginData)
                    .post();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        String body = loginPage.body().html();

        StringTokenizer st = new StringTokenizer(body, "('),; ");

        while(st.hasMoreTokens()) {
            if(st.nextToken().equals("SetCookie")) {
                cookies.put(st.nextToken(), st.nextToken());
            }
        }

        if(cookies.get("SUSER_ID") == null) {
            return null;
        }

        return cookies;
    }

    private Map<String, String> getCookie(String url, Map<String, String> cookies) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url)
                    .timeout(5000)
                    .cookies(cookies)
                    .userAgent(userAgent)
                    .headers(headers)
                    .method(Connection.Method.GET)
                    .execute();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        assert response != null;

        return response.cookies();
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


}
