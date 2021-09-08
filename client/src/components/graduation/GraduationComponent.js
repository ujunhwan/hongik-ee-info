import React from 'react'

function GraduationComponent ({
    updateHandler,
    courseList,
    graduationHandler,
    courseHandler,
}) {
    return (
        <>
        <div>
            <button className="course-update" onClick={courseHandler}>
                COURSE UPDATE
            </button>
            </div>
            
            <div>
            <button className="graduation-update" onClick={graduationHandler}>
                GRADUATION UPDATE
            </button>
            <div>
                {courseList}
            </div>
            </div>
        </>
    )
}

export default GraduationComponent
