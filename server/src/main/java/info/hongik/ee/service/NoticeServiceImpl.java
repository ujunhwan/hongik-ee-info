package info.hongik.ee.service;

import info.hongik.ee.domain.Notice;
import info.hongik.ee.repository.NoticeRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public void crawling() {
        Notice notice = new Notice();
        String url = "http://www.hongik.ac.kr/front/boardlist.do?bbsConfigFK=76&siteGubun=1&menuGubun=1";
        Document doc = null;

        try {
            doc = Jsoup.connect(url)
                    .timeout(5000)
                    .get();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        assert doc != null;

        String titleSelector = ".subject";
        Elements page = doc.select("body");
        Elements titles = page.select(titleSelector);
        Elements urls = titles.select("a");

        System.out.println("titles size : " + titles.size());
        for(int i = 0; i < titles.size(); i++) {
            String linkHref = "http://www.hongik.ac.kr";
            linkHref += urls.get(i).attr("href");
            System.out.println(titles.get(i).text() + "\n" + linkHref + "\n");
        }

        // jsoup 이용해서 공지 목록 5개 가져옴
        // 첫번째가 같다면 continue
        // 같지 않다면, DB 전체를 지웠다가 다시 넣는다.

    }
}
