package info.hongik.ee.repository;

import info.hongik.ee.domain.Notice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaNoticeRepository implements NoticeRepository {

    private final EntityManager em;

    public JpaNoticeRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Notice save(Notice notice) {
        em.persist(notice);
        return notice;
    }

    @Override
    public Notice findById(Long id) {
        try {
            return em.find(Notice.class, id);
        } catch (NullPointerException e) {
            System.out.println(id + "값이 존재하지 않습니다.");
            return null;
        }
    }

    @Override
    public List<Notice> findAll() {
        String jpql = "select n from Notice n";
        return em.createQuery(jpql, Notice.class).getResultList();
    }
}
