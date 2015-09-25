package org.courseregistration.datatests;

import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;

import javax.persistence.EntityManager;
import java.util.Calendar;


public class DataGenerator {

    public static void generateData(){
        EntityManager entityManager = org.courseregistration.datatests.HibernateUtils.getEntityManager();

        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setMiddleName("R.");
        student1.setLastName("Edward");
        student1.setAddress1("201 W California Ave");
        student1.setAddress2("");
        student1.setCity("Sunnyvale");
        student1.setState("CA");
        student1.setCountry("USA");
        student1.setZipCode("94086");
        student1.setAdmissionType("Accepted");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1988,Calendar.FEBRUARY,5);
        student1.setDateOfBirth(calendar1.getTime());

        student1.setEmailId("John@gmail.com");
        student1.setPhoneNumber("+1 408 386 7260");
        student1.setPreviousDegree("Bsc Comp Science");
//==============================================================

        Student student2 = new Student();
        student2.setFirstName("Alice");
        student2.setMiddleName("W.");
        student2.setLastName("Campbell");
        student2.setAddress1("65 Rio Robles E");
        student2.setAddress2("");
        student2.setCity("San Jose");
        student2.setState("CA");
        student2.setCountry("USA");
        student2.setZipCode("95134");
        student2.setAdmissionType("Accepted - Conditionally");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1986,Calendar.AUGUST,7);
        student2.setDateOfBirth(calendar2.getTime());

        student2.setEmailId("alice@gmail.com");
        student2.setPhoneNumber("+1 408 376 7860");
        student2.setPreviousDegree("BE Comp Engineering");

        //==========================================
        Student student3 = new Student();
        student3.setFirstName("Sarah");
        student3.setMiddleName("V.");
        student3.setLastName("Rossie");
        student3.setAddress1("209 E Indio Ave");
        student3.setAddress2("");
        student3.setCity("Fremont");
        student3.setState("CA");
        student3.setCountry("USA");
        student3.setZipCode("94536");
        student3.setAdmissionType("Accepted");

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1987,Calendar.JULY,28);
        student3.setDateOfBirth(calendar1.getTime());

        student3.setEmailId("sarah@gmail.com");
        student3.setPhoneNumber("+1 508 664 7260");
        student3.setPreviousDegree("BE");
        //==========================================
        Student student4 = new Student();
        student4.setFirstName("John");
        student4.setMiddleName("R.");
        student4.setLastName("Edward");
        student4.setAddress1("201 W California Ave");
        student4.setAddress2("");
        student4.setCity("Sunnyvale");
        student4.setState("CA");
        student4.setCountry("USA");
        student4.setZipCode("94086");
        student4.setAdmissionType("Accepted");

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1988,Calendar.FEBRUARY,5);
        student4.setDateOfBirth(calendar1.getTime());

        student4.setEmailId("John@gmail.com");
        student4.setPhoneNumber("+1 408 386 7260");
        student4.setPreviousDegree("Bsc Comp Science");

        //==========================================
        Course course = new Course();
        course.setCode("CMPE-272");
        course.setName("Enterprise Software Platforms");
        course.setDepartment("Engineering");
        course.setDescription("Some Course Description");
        course.setNumOfCredits(3);
        course.setPrerequisiteCourse("Operating systems");
        course.setProgram("Graduate");


        Course course2 = new Course();
        course2.setCode("CMPE-283");
        course2.setName("Virtualization Technology");
        course2.setDepartment("Engineering");
        course2.setDescription("Some Course Description");
        course2.setNumOfCredits(3);
        course2.setPrerequisiteCourse("NONE");
        course2.setProgram("Graduate");
        //==========================================

        Professor professor1 = new Professor();

        professor1.setFirstName("Alice");
        professor1.setMiddleName("W.");
        professor1.setLastName("Campbell");
        professor1.setAddress1("65 Rio Robles E");
        professor1.setAddress2("");
        professor1.setCity("San Jose");
        professor1.setState("CA");
        professor1.setCountry("USA");
        professor1.setZipCode("95134");
        professor1.setFacultyType("Permanant");
        professor1.setYearsOfExperience(12);

        Calendar prof_calendar1 = Calendar.getInstance();
        prof_calendar1.set(1986,Calendar.AUGUST,7);
        professor1.setDateOfBirth(prof_calendar1.getTime());

        professor1.setEmailId("alice@gmail.com");
        professor1.setPhoneNumber("+1 408 376 7860");

        Calendar prof1_calendar1 = Calendar.getInstance();
        prof1_calendar1.set(0,Calendar.JULY,0,10,0);
        professor1.setOfficeHoursFromTime(prof1_calendar1.getTime());

        Calendar prof1_calendar2 = Calendar.getInstance();
        prof1_calendar2.set(0,Calendar.MAY,0,17,0);
        professor1.setOfficeHoursToTime(prof1_calendar2.getTime());
    //===================================================================

        Section section1 = new Section();

        Calendar classEndDateTime = Calendar.getInstance();
        classEndDateTime.set(2015,Calendar.DECEMBER,12,18,0);

        section1.setClassEndTime(classEndDateTime.getTime());

        Calendar classStartDateTime = Calendar.getInstance();
        classStartDateTime.set(2015,Calendar.AUGUST,24,20,45);

        section1.setClassStartTime(classStartDateTime.getTime());

        section1.setDayOfWeek("Thursday");
        section1.setEndDate(classEndDateTime.getTime());
        section1.setStartDate(classStartDateTime.getTime());

        section1.setModeOfInstruction("In Class");
        section1.setRoomNumber("EM-501");
        section1.setSemester("Fall-2015");
        section1.setTotalCapacity(40);
        section1.setWaitListCapacity(20);





        section1.setProfessor(professor1);
        section1.setCourse(course);


        entityManager.getTransaction().begin();
        entityManager.persist(course2);
        entityManager.persist(student2);
        entityManager.persist(professor1);

        entityManager.persist(section1);
        student1.addSection(section1);
        entityManager.persist(student1);
        entityManager.getTransaction().commit();

        Student result= entityManager.find(Student.class, student1.getId());

        System.out.println("****************"+result.getFirstName()+"****************");




    }



}
