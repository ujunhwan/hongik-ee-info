package info.hongik.ee.repository;

import info.hongik.ee.domain.user.User;

public interface UserRepository {
    public void save(User user);
    public User findOne(Long userId);
    public User findBysId(String studentId);

}
