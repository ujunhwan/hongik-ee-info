package info.hongik.ee.service;

import info.hongik.ee.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public interface UserService {
    public User scrapeUserInfo();
    public HashMap<String, Long> classifySubjects(HashSet<Long> subjects);
    public HashMap<Long, String> getSubjectCodeByField();
}
