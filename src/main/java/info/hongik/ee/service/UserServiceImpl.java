package info.hongik.ee.service;

import info.hongik.ee.domain.LoginDto;
import info.hongik.ee.domain.course.CourseDto;
import info.hongik.ee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static org.jsoup.Connection.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Map<String, String> login(LoginDto loginDto) throws IOException {
        String classnetUrl = "https://cn.hongik.ac.kr";
        String classnetMainUrl = "https://cn.hongik.ac.kr/stud";

        Map<String, String> cookies = new HashMap<>();
        Map<String, String> userCookie = getUserCookie(loginDto);
        if(userCookie == null) {
            System.out.println("null in login function");
            return null;
        }

        cookies.putAll(userCookie);
        cookies.putAll(getCookie(classnetUrl, cookies, getHeaders(null, null), null, Method.POST));
        cookies.putAll(getCookie(classnetMainUrl, cookies, getHeaders(null, null), null, Method.POST));

        return cookies;
    }

    @Override
    public boolean logout() {
        // 임시
        return true;
    }

    @Override
    public List<CourseDto> getCourses(HttpServletRequest request) throws IOException {
        String url = "https://cn.hongik.ac.kr/stud/P/01000/01000.jsp";
        Map<String, String> cookies = extractCookie(request); // 지워도됨
        Document document = getDocument(url, extractCookie(request), getHeaders(null, null), null, Method.POST);

        for (String key : cookies.keySet()) {
            System.out.println(key + " :: " + cookies.get(key));
        }

        List<CourseDto> courses = new ArrayList<>();
        Elements semesters = document.getElementById("body").select(".table0");
        for(Element gradeTable : semesters) {
            Elements rows = gradeTable.selectFirst("tbody").children();
            for(Element row : rows) {
                Elements classInfo = row.children();
                if(row.nextElementSibling() == null) continue;
                if(isTakenClass(classInfo)) {
                    String courseNumber = classInfo.first().text();
                    String courseName = classInfo.first().nextElementSibling().text();
                    String grade = classInfo.last().previousElementSibling().text();
                    String credit = classInfo.last().previousElementSibling().previousElementSibling().text();
                    courses.add(new CourseDto(courseName, courseNumber, credit, grade, "idontknow"));
                }
            }
        }

        return courses;
    }

    @Override
    public String getGraduationRequirement(HttpServletRequest request) throws IOException {
        String url = "https://cn.hongik.ac.kr/stud/E/04000/04010.jsp";
        String referer = "https://cn.hongik.ac.kr/stud/E/04000/04000.jsp";

        for(int j = 0; j <= 25; j++) {
            for(int i = 1; i <= 300; i++) {
                int deptFirst = 65+j;

                String dept = String.valueOf((char)deptFirst);
                if (i < 10) {
                    dept += "00";
                } else if (i < 100) {
                    dept += "0";
                }
                dept += Integer.toString(i);
                Map<String, String> formData = new HashMap<>();
                formData.put("dept", dept);
                formData.put("hakbun", "B615125");
                Document document = getDocument(url, extractCookie(request), getHeaders(referer, null), formData, Method.POST);
                Element element = document.selectFirst(".sub_title");
                String text = element.text();
                if (text.charAt(0) == '(') continue;
                int index = text.indexOf(')');
                String deptCode = text.substring(0, index+1);
                System.out.println(deptCode);
            }
        }

        return null;
    }

    @Override
    public String getMajorFromId(String id) {
        System.out.println("major : " + id.substring(2, 4));
        return id.substring(2, 4);
    }

    @Override
    public String getYearFromId(String id) {
        int studentId = 0;
        char firstDigit = id.charAt(0);
        char secondDigit = id.charAt(1);

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

    private Map<String, String> getUserCookie(LoginDto loginDto) throws IOException {
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put("USER_ID", loginDto.getId());
        data.put("PASSWD", loginDto.getPasswd());

        String loginPageUrl = "https://ap.hongik.ac.kr/login/LoginExec3.php";
        Document loginPage = getDocument(loginPageUrl, null, getHeaders(null, null), data, Method.POST);

        Optional<String> mayBody = Optional.ofNullable(loginPage.body().html());
        String body = mayBody.orElse("0");
        StringTokenizer st = new StringTokenizer(body, "('),; ");
        while(st.hasMoreTokens()) {
            if(st.nextToken().equals("SetCookie")) {
                cookies.put(st.nextToken(), st.nextToken());
            }
        }

        if(cookies.get("SUSER_ID") == null) return null;
        return cookies;
    }

    private Map<String, String> getCookie(String url, Map<String, String> cookies, Map<String, String> headers, Map<String, String> data, Method method) throws IOException {
        return getResponse(url, cookies, headers, data, method).cookies();
    }

    private Document getDocument(String url, Map<String ,String> cookies, Map<String, String> headers, Map<String, String> data, Method method) throws IOException {
        return getResponse(url, cookies, headers, data, method).parse();
    }

    private Response getResponse(String url, Map<String, String> cookies, Map<String, String> headers, Map<String, String> data, Method method) throws IOException {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36";
        Connection conn = Jsoup.connect(url)
                .timeout(3000)
                .userAgent(userAgent)
                .headers(headers)
                .method(method);
        if(cookies != null) conn.cookies(cookies);
        if(data != null) conn.data(data);

        Response response = null;
        try {
            response = conn.execute();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    private Map<String, String> getHeaders(String referer, String origin) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        if(referer != null) headers.put("Referer", referer);
        if(origin != null) headers.put("Origin", origin);
        else headers.put("Origin", "https://cn.hongik.ac.kr");
        return headers;
    }

    private boolean isTakenClass(Elements subjectInfo) {
        return !subjectInfo.last().text().equals("재수강") && !subjectInfo.last().previousElementSibling().text().equals("F");
    }

    private Map<String, String> extractCookie(HttpServletRequest request) {
        Map<String, String> cookies = new HashMap<>();
        // TODO: NULL 처리
        Cookie[] cookieArray = request.getCookies();

        for(Cookie cookie : cookieArray) {
            cookies.put(cookie.getName(), cookie.getValue());
        }

        return cookies;
    }
}
