package info.hongik.ee.service;

import info.hongik.ee.repository.JpaNoticeRepository;
import info.hongik.ee.repository.NoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class NoticeServiceTest {

    @Autowired NoticeService noticeService;
    @Autowired NoticeRepository noticeRepository;

    @Test
    public void 가져오기() throws IOException {
        //String selector = "div.w3-container h4";
        noticeService.crawling();
    }

}
