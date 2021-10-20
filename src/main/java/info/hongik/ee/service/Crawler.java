package info.hongik.ee.service;

import info.hongik.ee.domain.user.LoginDto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

@Component
public class Crawler {
    public Map<String, String> getUserCookie(LoginDto loginDto) throws IOException {
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put("USER_ID", loginDto.getId());
        data.put("PASSWD", loginDto.getPasswd());

        String loginPageUrl = "https://ap.hongik.ac.kr/login/LoginExec3.php";
        Document loginPage = getDocument(loginPageUrl, null, getHeaders(null, null), data, Connection.Method.POST);

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

    public Map<String, String> getCookie(String url, Map<String, String> cookies, Map<String, String> headers, Map<String, String> data, Connection.Method method) throws IOException {
        return getResponse(url, cookies, headers, data, method).cookies();
    }

    public Document getDocument(String url, Map<String ,String> cookies, Map<String, String> headers, Map<String, String> data, Connection.Method method) throws IOException {
        return getResponse(url, cookies, headers, data, method).parse();
    }

    public Connection.Response getResponse(String url, Map<String, String> cookies, Map<String, String> headers, Map<String, String> data, Connection.Method method) throws IOException {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36";
        Connection conn = Jsoup.connect(url)
                .timeout(3000)
                .userAgent(userAgent)
                .headers(headers)
                .method(method);
        if(cookies != null) conn.cookies(cookies);
        if(data != null) conn.data(data);

        Connection.Response response = null;
        try {
            response = conn.execute();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public Map<String, String> getHeaders(String referer, String origin) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        if(referer != null) headers.put("Referer", referer);
        if(origin != null) headers.put("Origin", origin);
        else headers.put("Origin", "https://cn.hongik.ac.kr");
        return headers;
    }

    public Map<String, String> extractCookie(HttpServletRequest request) {
        Map<String, String> cookies = new HashMap<>();
        // TODO: NULL 처리
        Cookie[] cookieArray = request.getCookies();

        for(Cookie cookie : cookieArray) {
            if(cookie == null) break;
            cookies.put(cookie.getName(), cookie.getValue());
        }

        return cookies;
    }
}
