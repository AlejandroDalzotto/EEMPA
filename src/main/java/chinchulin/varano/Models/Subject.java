package chinchulin.varano.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "subject")
@NoArgsConstructor
public class Subject {

    @Id
    @Column(name = "id_subject")
    Long id_subject;

    @Column(name = "name")
    String name;

    @Column(name = "active")
    Boolean active;

    @ManyToMany
    @JoinTable(name = "student-subject", joinColumns = @JoinColumn(name = "id_subject"), inverseJoinColumns = @JoinColumn(name = "id_student"))
    List<Student> students;

    @ManyToOne
    @JoinColumn(name = "id_module")
    Module id_module;
}
