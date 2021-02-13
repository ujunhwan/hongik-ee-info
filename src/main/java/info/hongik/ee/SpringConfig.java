package info.hongik.ee;

import info.hongik.ee.repository.JpaNoticeRepository;
import info.hongik.ee.repository.NoticeRepository;
import info.hongik.ee.service.NoticeService;
import info.hongik.ee.service.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public NoticeService noticeService() {
        return new NoticeServiceImpl(noticeRepository());
    }

    @Bean
    public NoticeRepository noticeRepository() {
        return new JpaNoticeRepository(em);
    }

}
