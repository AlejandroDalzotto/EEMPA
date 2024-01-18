package chinchulin.varano.Models;

import java.util.List;

import lombok.Data;

@Data
public class StudentAnswer {

    int pages;
    List<Student> students;
}