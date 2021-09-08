import axios from 'axios'
import React, { useState } from 'react'
import GraduationComponent from '../../components/graduation/GraduationComponent'

function GraduationContainer() {
    const courseUrl = "/api/user/courses";
    const graduationUrl = "/api/user/graduation";
    const config = {
        withCredentials: true
    }

    const [courses, setCourses] = useState([]);

    const updateHandler = (event) => {
        console.log("Graduation!");
        
        axios.get(graduationUrl, config)
            .then(res => console.log(res))
    }

    const courseList = courses.map((course, count) => <li key={count}>{course.courseNumber} {course.courseName} {course.grade} {course.credit}학점</li>)

    const courseHandler = () => {
        const url = "/api/user/courses";
        axios.get(url, config)
            .then(res => {
                setCourses(res.data);
                console.log(res.data);
            })
    }

    const graduationHandler = () => {
        const url = "/api/user/graduation";
        axios.get(url, config)
            .then(res => console.log(res))    
    }

    return (
        <>
            <GraduationComponent 
                updateHandler={updateHandler}
                courseHandler={courseHandler}
                graduationHandler={graduationHandler}
                courseList={courseList}
            />
        </>
    )
}

export default GraduationContainer
