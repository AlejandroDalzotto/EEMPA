package chinchulin.varano.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "module")
public class Module {

    @Id
    @Column(name = "id_module")
    Long id_module;

    @Column(name = "name")
    @Nonnull
    String name;

    @Column(name = "active")
    Boolean active;

     @JsonBackReference(value = "subjects")
    @OneToMany(mappedBy = "id_subject")
    List<Subject> subjects;
}
