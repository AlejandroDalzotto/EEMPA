package chinchulin.varano.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @JsonBackReference(value="subjects")
    @ManyToMany(mappedBy = "subjects")
    List<Student> students;

    @JsonBackReference(value = "id_module")
    @ManyToOne
    @JoinColumn(name = "id_module")
    Module id_module;
}
