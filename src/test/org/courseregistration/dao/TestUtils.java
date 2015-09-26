package org.courseregistration.dao;

import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;

import java.util.Calendar;

public class TestUtils {

    public static Section createSection(Professor professor,Course course){
        Section section = new Section();

        Calendar classEndDateTime = Calendar.getInstance();
        classEndDateTime.set(2015,Calendar.DECEMBER,12,18,0);

        section.setClassEndTime(classEndDateTime.getTime());

        Calendar classStartDateTime = Calendar.getInstance();
        classStartDateTime.set(2015,Calendar.AUGUST,24,20,45);

        section.setClassStartTime(classStartDateTime.getTime());

        section.setDayOfWeek("Thursday");
        section.setEndDate(classEndDateTime.getTime());
        section.setStartDate(classStartDateTime.getTime());

        section.setModeOfInstruction("In Class");
        section.setRoomNumber("EM-501");
        section.setSemester("Fall-2015");
        section.setTotalCapacity(40);
        section.setWaitListCapacity(20);
        section.setProfessor(professor);
        section.setCourse(course);
        return section;
    }
}
