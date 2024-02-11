package chinchulin.varano.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "student_subject")
public class Student_subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_subject_id")
    Long student_subject_Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student")
    Student id_student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_subject")
    Subject id_subject;
}
