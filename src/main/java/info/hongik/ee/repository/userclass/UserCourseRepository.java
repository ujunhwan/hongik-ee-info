package info.hongik.ee.repository.userclass;

import info.hongik.ee.domain.taken.Taken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCourseRepository {

    private final EntityManager em;

    // user가 수강한 수업 전부 가져오기
    public List<Taken> findAllTakenCourses(Long id) {
        return null;
    }
}
