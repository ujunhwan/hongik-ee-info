package info.hongik.ee.repository;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findOne(Long userId) {
        String query = "select u from User u where u.id = :id";
        return em.createQuery(query, User.class)
                .setParameter("id", userId)
                .getSingleResult();
    }

    @Override
    public User findBysId(String studentId) {
        String query = "select u from User u where u.studentId = :studentId";
        List<User> findUsers = em.createQuery(query, User.class)
                .setParameter("studentId", studentId)
                .getResultList();
        if(findUsers.isEmpty()) return null;
        return findUsers.get(0);
    }

    public Long findClass(Long userId, String classNumber) {
        String query = "select c from CourseDto c where c.classNumber=:classNumber";
        Course findCourse = em.createQuery(query, Course.class)
                .setParameter("classNumber", classNumber)
                .getSingleResult();

        return findCourse.getId();
    }

}
