package app;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StudentDaoImpl studentDao = new StudentDaoImpl();

        Student student = new Student();
        student.setFirstName("Niсk");
        student.setLastName("Test");
        student.setEmail("test@gamil.com");

        Homework homework = new Homework();
        homework.setDescription("Hibernate");
        homework.setDeadline(LocalDate.now().plusDays(7));
        homework.setMark(12);

        student.addHomework(homework);

        studentDao.save(student);

        System.out.println("Done");
    }
}
