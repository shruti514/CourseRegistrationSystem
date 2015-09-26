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
        student3.setPreviousDegree("Bachelor of commerce");
        //==========================================
        Student student4 = new Student();
        student4.setFirstName("Alan");
        student4.setMiddleName("V.");
        student4.setLastName("Simon");
        student4.setAddress1("4356 Waterford Ave");
        student4.setAddress2("");
        student4.setCity("San Diego");
        student4.setState("CA");
        student4.setCountry("USA");
        student4.setZipCode("45632");
        student4.setAdmissionType("Rejected");

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1983,Calendar.SEPTEMBER,5);
        student4.setDateOfBirth(calendar1.getTime());

        student4.setEmailId("V.Alan@gmail.com");
        student4.setPhoneNumber("+1 407 654 8760");
        student4.setPreviousDegree("Bachelor of Arts");

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
        prof_calendar1.set(1986, Calendar.AUGUST, 7);
        professor1.setDateOfBirth(prof_calendar1.getTime());

        professor1.setEmailId("alice@gmail.com");
        professor1.setPhoneNumber("+1 408 376 7860");

        Calendar prof1_calendar1 = Calendar.getInstance();
        prof1_calendar1.set(0, Calendar.JULY, 0, 10, 0);
        professor1.setOfficeHoursFromTime(prof1_calendar1.getTime());

        Calendar prof1_calendar2 = Calendar.getInstance();
        prof1_calendar2.set(0, Calendar.MAY, 0, 17, 0);
        professor1.setOfficeHoursToTime(prof1_calendar2.getTime());

        Professor professor2 = new Professor();

        professor2.setFirstName("Alice");
        professor2.setMiddleName("W.");
        professor2.setLastName("Campbell");
        professor2.setAddress1("65 Rio Robles E");
        professor2.setAddress2("");
        professor2.setCity("San Jose");
        professor2.setState("CA");
        professor2.setCountry("USA");
        professor2.setZipCode("95134");
        professor2.setFacultyType("Permanant");
        professor2.setYearsOfExperience(12);

        Calendar prof_calendar2 = Calendar.getInstance();
        prof_calendar2.set(1986, Calendar.AUGUST, 7);
        professor2.setDateOfBirth(prof_calendar2.getTime());

        professor2.setEmailId("alice@gmail.com");
        professor2.setPhoneNumber("+1 408 376 7860");

        Calendar prof2_calendar1= Calendar.getInstance();
        prof2_calendar1.set(0, Calendar.JULY, 0, 10, 0);
        professor1.setOfficeHoursFromTime(prof2_calendar1.getTime());

        Calendar prof2_calendar2 = Calendar.getInstance();
        prof2_calendar2.set(0, Calendar.MAY, 0, 17, 0);
        professor2.setOfficeHoursToTime(prof2_calendar2.getTime());

        Professor professor3 = new Professor();

        professor3.setFirstName("Alice");
        professor3.setMiddleName("W.");
        professor3.setLastName("Campbell");
        professor3.setAddress1("65 Rio Robles E");
        professor3.setAddress2("");
        professor3.setCity("San Jose");
        professor3.setState("CA");
        professor3.setCountry("USA");
        professor3.setZipCode("95134");
        professor3.setFacultyType("Permanant");
        professor3.setYearsOfExperience(12);

        Calendar prof_calendar3 = Calendar.getInstance();
        prof_calendar3.set(1986, Calendar.AUGUST, 7);
        professor3.setDateOfBirth(prof_calendar3.getTime());

        professor3.setEmailId("alice@gmail.com");
        professor3.setPhoneNumber("+1 408 376 7860");

        Calendar prof3_calendar1 = Calendar.getInstance();
        prof3_calendar1.set(0, Calendar.JULY, 0, 10, 0);
        professor3.setOfficeHoursFromTime(prof3_calendar1.getTime());

        Calendar prof3_calendar2 = Calendar.getInstance();
        prof3_calendar2.set(0, Calendar.MAY, 0, 17, 0);
        professor1.setOfficeHoursToTime(prof3_calendar2.getTime());

        Professor professor4 = new Professor();

        professor4.setFirstName("Alice");
        professor4.setMiddleName("W.");
        professor4.setLastName("Campbell");
        professor4.setAddress1("65 Rio Robles E");
        professor4.setAddress2("");
        professor4.setCity("San Jose");
        professor4.setState("CA");
        professor4.setCountry("USA");
        professor4.setZipCode("95134");
        professor4.setFacultyType("Permanant");
        professor4.setYearsOfExperience(12);

        Calendar prof_calendar4 = Calendar.getInstance();
        prof_calendar4.set(1986, Calendar.AUGUST, 7);
        professor4.setDateOfBirth(prof_calendar4.getTime());

        professor4.setEmailId("alice@gmail.com");
        professor4.setPhoneNumber("+1 408 376 7860");

        Calendar prof4_calendar1 = Calendar.getInstance();
        prof4_calendar1.set(0, Calendar.JULY, 0, 10, 0);
        professor4.setOfficeHoursFromTime(prof4_calendar1.getTime());

        Calendar prof4_calendar2 = Calendar.getInstance();
        prof4_calendar2.set(0, Calendar.MAY, 0, 17, 0);
        professor4.setOfficeHoursToTime(prof4_calendar2.getTime());

        Professor professor5 = new Professor();

        professor5.setFirstName("Alice");
        professor5.setMiddleName("W.");
        professor5.setLastName("Campbell");
        professor5.setAddress1("65 Rio Robles E");
        professor5.setAddress2("");
        professor5.setCity("San Jose");
        professor5.setState("CA");
        professor5.setCountry("USA");
        professor5.setZipCode("95134");
        professor5.setFacultyType("Permanant");
        professor5.setYearsOfExperience(12);

        Calendar prof_calendar5 = Calendar.getInstance();
        prof_calendar5.set(1986, Calendar.AUGUST, 7);
        professor5.setDateOfBirth(prof_calendar5.getTime());

        professor5.setEmailId("alice@gmail.com");
        professor5.setPhoneNumber("+1 408 376 7860");

        Calendar prof5_calendar1 = Calendar.getInstance();
        prof5_calendar1.set(0, Calendar.JULY, 0, 10, 0);
        professor5.setOfficeHoursFromTime(prof5_calendar1.getTime());

        Calendar prof5_calendar2 = Calendar.getInstance();
        prof5_calendar2.set(0, Calendar.MAY, 0, 17, 0);
        professor5.setOfficeHoursToTime(prof5_calendar2.getTime());
    //===================================================================
        Course  course1=new Course();

        course1.setCode("");
        course1.setName("");
        course1.setDescription("");
        course1.setDepartment("");
        course1.setNumOfCredits(9);
        course1.setPrerequisiteCourse("");
        course1.setProgram("");

        Course  course2=new Course();

        course2.setCode("");
        course2.setName("");
        course2.setDescription("");
        course2.setDepartment("");
        course2.setNumOfCredits(9);
        course2.setPrerequisiteCourse("");
        course2.setProgram("");

        Course  course3=new Course();

        course3.setCode("");
        course3.setName("");
        course3.setDescription("");
        course3.setDepartment("");
        course3.setNumOfCredits(9);
        course3.setPrerequisiteCourse("");
        course3.setProgram("");

        Course  course4=new Course();

        course4.setCode("");
        course4.setName("");
        course4.setDescription("");
        course4.setDepartment("");
        course4.setNumOfCredits(9);
        course4.setPrerequisiteCourse("");
        course4.setProgram("");

        Course  course5=new Course();

        course5.setCode("");
        course5.setName("");
        course5.setDescription("");
        course5.setDepartment("");
        course5.setNumOfCredits(9);
        course5.setPrerequisiteCourse("");
        course5.setProgram("");

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
        section1.setCourse(course1);


        entityManager.getTransaction().begin();
        entityManager.persist(course1);
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
