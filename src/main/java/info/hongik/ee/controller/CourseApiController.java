package info.hongik.ee.controller;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.payload.CourseCreateRequest;
import info.hongik.ee.domain.course.CourseDto;
import info.hongik.ee.payload.CourseCrawlRequest;
import info.hongik.ee.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseApiController {

    private final CourseService courseService;

    @GetMapping("")
    public List<CourseDto> displayCourseList() {
        List<Course> courses = courseService.findAll();
        List<CourseDto> courseDtos = courses.stream()
                .map(CourseDto::new).collect(Collectors.toList());
        return courseDtos;
    }

    @PostMapping("")
    public Long createCourse(
            @RequestBody CourseCreateRequest request
    ) {
        Course course = new Course(
                request.getCourseNumber(),
                request.getCourseName(),
                request.getCredit(),
                request.getType()
        );

        Long savedCourseId = courseService.save(course);
        return savedCourseId;
    }


    @GetMapping("/{course_id}")
    public CourseDto displayCourseDetail(
            @PathVariable("course_id") Long course_id
    ) {
        Course findCourse = courseService.findById(course_id);
        CourseDto findCourseDto = new CourseDto(findCourse);
        return findCourseDto;
    }

    @PutMapping("/{course_id}")
    public CourseDto modifyCourse(
            @PathVariable("course_id") Long course_id) {
        return null;
    }

    @DeleteMapping("/{course_id}")
    public void deleteCourse(
            @PathVariable("course_id") Long course_id
    ) {
        courseService.deleteById(course_id);
    }

    /**
     *  p_yy;
     *  p_hakgi;
     *  p_dept;
     *
     */
    @PostMapping("/all")
    public List<CourseDto> takeAllAndCreateCourses(
            @RequestBody CourseCrawlRequest request
    ) {
        List<CourseDto> savedCourses = courseService.crawlAllCourses(request);
        return savedCourses;
    }

}
