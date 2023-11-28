package chinchulin.varano.Models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id_student")
    Long id_student;

    @Column(name = "name")
    @Nonnull
    String name;

    @Column(name = "lastName")
    @Nonnull
    String lastName;

    @Column(name = "birth")
    @Nonnull
    Date birth;

    @Column(name = "sex")
    String sex;

    @Column(name = "address")
    @Nonnull
    String address;

    @Column(name = "dni")
    @Nonnull
    Integer dni;

    @Column(name = "cellPhone")
    Long cellPhone;

    @Column(name = "linePhone")
    Long linePhone;

    @Column(name = "age")
    int age;

    @Column(name = "mail")
    String mail;

    @Column(name = "legajo")
    Long lejago;

    @Column(name = "matricula")
    Long matricula;

    @Column(name = "birthCert")
    Boolean birthCert;

    @Column(name = "studyCert")
    Boolean studyCert;

    // acá decidí ponerle curso porque si le ponía año nos ibamos a confundir
    @Column(name = "course")
    int course;

    @Column(name = "disability")
    Boolean disability;

    @Column(name = "health")
    Boolean health;

    @Column(name = "active")
    Boolean active;

    @ManyToMany
    @JsonBackReference(value = "subjects")
    @JoinTable(name = "student-subject", joinColumns = @JoinColumn(name = "id_student"), inverseJoinColumns = @JoinColumn(name = "id_subject"))
    List<Subject> subjects;
}
