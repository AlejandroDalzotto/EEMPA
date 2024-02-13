package chinchulin.varano.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_module;

    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();
}
