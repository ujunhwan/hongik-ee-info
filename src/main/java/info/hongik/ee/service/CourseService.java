package info.hongik.ee.service;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.course.CourseDto;
import info.hongik.ee.domain.course.CourseEnum;
import info.hongik.ee.payload.CourseCrawlRequest;
import info.hongik.ee.repository.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import static org.jsoup.Connection.Method.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final Crawler crawler;

    @Transactional
    public Long save(Course course) {
        validateDuplicatedCourse(course.getCourseNumber());
        Course savedCourse = courseRepository.save(course);
        Long id = savedCourse.getId();
        return id;
    }

    private void validateDuplicatedCourse(String courseNumber) {
        Course findCourse = courseRepository.findCourseOneByCourseNumber(courseNumber);
        if(findCourse != null)
            throw new IllegalStateException("이미 있는 과목입니다.");
    }

    public Course findById(Long id) {
        return courseRepository.findCourseOneById(id);
    }

    public List<Course> findByCourseName(String courseName) {
        List<Course> courses = courseRepository.findCourseListByCourseName(courseName);
        return courses;
    }

    public Course modifiyCourse() {
        // 동적으로 받아야됨,,
        return null;
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Course> findCourse = courseRepository.findById(id);
        if(findCourse.isPresent()) {
            courseRepository.deleteById(id);
        }
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Transactional
    public List<CourseDto> crawlAllCourses(CourseCrawlRequest request) {

        List<CourseDto> courses = new ArrayList<>();
        String url = "https://sugang.hongik.ac.kr/cn50001.jsp";

        Map<String, String> headers = crawler.getHeaders("https://sugang.hongik.ac.kr/cn50000.jsp", "https://sugang.hongik.ac.kr");
        Map<String, String> cookies = new HashMap<>();
        Map<String, String> data = new HashMap<>();

        data.put("p_yy", request.p_yy);
        data.put("p_hakgi", request.p_hakgi);
        data.put("p_dept", request.p_dept);
        data.put("p_campus", "1");
        data.put("p_gubun", "2");
        data.put("p_grade", "0");
        data.put("p_abeek", "0");
        data.put("p_ibhak", "2016");

        try {
            Document document = crawler.getDocument(url, cookies, headers, data, POST);
//            System.out.println("document.html() = " + document.html());
            Element tbody = document.getElementById("select_list").child(0);

            tbody.child(0).remove();
            tbody.child(tbody.childrenSize() - 1).remove();
            tbody.child(tbody.childrenSize() - 1).remove();

            Elements tuples = tbody.children();
            for (Element tuple : tuples) {
                // 학년, 학과 ,학과, 이수구분, 학수번호, 과목명, 수업형태, 학점, 제한인원, 수강인원, 폐강인원, 교수명, 요일, 비고
                CourseEnum type = CourseEnumMapper(tuple.child(3).text());
                String courseNumber = CourseNumberParser(tuple.child(4).text());
                String courseName = tuple.child(5).text();
                String credit = tuple.child(7).text();

                Course course = new Course(courseNumber, courseName, credit, type);

                try {
                    System.out.println("course = " + course);
                    validateDuplicatedCourse(courseNumber);
                    courseRepository.save(course);
                    courses.add(new CourseDto(course));
                } catch (IllegalStateException e) {
                    System.out.println("이미 있는 과목입니다." + e);
                }
            }

        } catch (Exception e) {
            System.out.println("에러가 발생했습니다. : " + e);
        }
        return courses;
    }

    private CourseEnum CourseEnumMapper(String type) {
        CourseEnum ret = CourseEnum.ELECTIVE;
        switch(type) {
            case "전필":
                ret = CourseEnum.REQUIRED;
                break;

            case "전선":
                ret = CourseEnum.SELECTIVE;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        return ret;
    }

    private String CourseNumberParser(String courseNumber) {
        return courseNumber.substring(0, 6);
    }

}
