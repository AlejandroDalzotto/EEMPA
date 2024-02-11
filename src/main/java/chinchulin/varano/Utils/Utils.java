package chinchulin.varano.Utils;

import chinchulin.varano.Models.Student;
import chinchulin.varano.Payloads.Request.StudentRequest;

import java.sql.Date;
import java.util.Collections;

public class Utils {

    public static void copyStudentProperties(StudentRequest student, Student studentToSave) {
        studentToSave.setAddress(student.getAddress());
        studentToSave.setBirthCert(student.getBirthCert());
        studentToSave.setBirth(student.getBirth());
        studentToSave.setCellPhone(student.getCellPhone());
        studentToSave.setCourse(student.getCourse());
        studentToSave.setDisability(student.getDisability());
        studentToSave.setDni(student.getDni());
        studentToSave.setHealth(student.getHealth());
        studentToSave.setLastName(student.getLastName());
        studentToSave.setLegajo(student.getLegajo());
        studentToSave.setLinePhone(student.getLinePhone());
        studentToSave.setMail(student.getMail());
        studentToSave.setMatricula(student.getMatricula());
        studentToSave.setName(student.getName());
        studentToSave.setSex(student.getSex());
        studentToSave.setStudyCert(student.getStudyCert());
    }
}
