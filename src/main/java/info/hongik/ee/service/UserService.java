package info.hongik.ee.service;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public interface UserService {
    public void scrapeUserInfo(Map<String, String> cookies);
    public boolean userLogin(LoginInfo loginInfo);
    public HashMap<String, Long> classifySubjects(HashSet<Long> subjects);
    public HashMap<Long, String> getSubjectCodeByField();
}
