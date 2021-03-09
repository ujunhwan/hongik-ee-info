package info.hongik.ee;

import info.hongik.ee.repository.*;
import info.hongik.ee.service.*;
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

    @Bean
    public UserInfoRepository userInfoRepository() {
        return new MemoryUserInfoRepository();
    }

    @Bean
    public ClassInfoRepository classInfoRepository() {
        return new MemoryClassInfoRepository();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userInfoRepository(), classInfoRepository());
    }

    @Bean
    public SecurityService securityService() {
        return new SecurityServiceImpl(userInfoRepository());
    }

}
