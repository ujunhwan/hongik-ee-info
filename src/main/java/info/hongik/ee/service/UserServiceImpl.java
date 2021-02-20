package info.hongik.ee.service;


import info.hongik.ee.domain.User;

import java.util.HashMap;
import java.util.HashSet;

public class UserServiceImpl implements UserService {

    @Override
    public User scrapeUserInfo() {
        User user = new User();

        HashSet<Long> subjects = new HashSet<>();
        user.setSubjectsTaken(classifySubjects(subjects));
        return user;
    }

    @Override
    public HashMap<String, Long> classifySubjects(HashSet<Long> subjects) {
        HashMap<String, Long> classified = new HashMap<>();
        HashMap<Long, String> subjectCodeByField = getSubjectCodeByField();

        for(Long subject : subjects) {
            classified.put(subjectCodeByField.get(subject), subject);
        }

        return classified;
    }

    public HashMap<Long, String> getSubjectCodeByField() {
        // DB에서 가져옴
        // 학수번호, 분야
        HashMap<Long, String> subjectCode = new HashMap<>();
        return subjectCode;
    }
}
