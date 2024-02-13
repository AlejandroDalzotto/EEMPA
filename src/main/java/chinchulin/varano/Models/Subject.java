package chinchulin.varano.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "subject")
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_subject;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_module")
    private Module module;
}
