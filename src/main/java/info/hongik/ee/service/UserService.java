package info.hongik.ee.service;

import info.hongik.ee.domain.LoginInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    public boolean login(LoginInfo loginInfo);
    public boolean logout();
    public void crawlUserInfo(Map<String, String> cookies);
    public List<Long> getClassifiedClasses(Long fieldId);
}
