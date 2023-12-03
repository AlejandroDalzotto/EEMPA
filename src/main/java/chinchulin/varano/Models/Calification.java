package chinchulin.varano.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "calification")
public class Calification {

    @Id
    @Column(name = "id_calification")
    Long id_calification;

    @Column(name ="value")
    String value;

    
}
