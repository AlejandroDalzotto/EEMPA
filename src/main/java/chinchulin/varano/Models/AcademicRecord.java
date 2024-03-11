package chinchulin.varano.Models;

import chinchulin.varano.Utils.AcademicState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "academic_records")
public class AcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_academic_record;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_course")
    private Course course;

    @Column(name = "study_year")
    private Short studyYear;

    @Enumerated(EnumType.STRING)
    private AcademicState state = AcademicState.REGULAR;

    @Column(name = "average", nullable = false)
    private Double average = 0d;

    @Column(name = "unique_record_code", nullable = false, unique = true)
    private String uniqueCode;

    @Column(name = "referred_comment")
    private String comment = null;
}
