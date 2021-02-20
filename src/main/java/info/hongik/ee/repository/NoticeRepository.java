package info.hongik.ee.repository;

import info.hongik.ee.domain.Notice;

import java.util.List;

public interface NoticeRepository {
    Notice save(Notice notice);
    Notice findById(Long id);
    List<Notice> findAll();
}
