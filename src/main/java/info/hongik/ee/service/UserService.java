package info.hongik.ee.service;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
    public boolean login(LoginInfo loginInfo);
    public void crawlUserInfo();
    public List<Long> getClassifiedClasses(Long fieldId);
}
