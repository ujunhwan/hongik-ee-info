package info.hongik.ee.service;

import info.hongik.ee.domain.LoginInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public boolean login(LoginInfo loginInfo);
    public boolean logout();
    public void crawlUserInfo();
    public List<Long> getClassifiedClasses(Long fieldId);
}
