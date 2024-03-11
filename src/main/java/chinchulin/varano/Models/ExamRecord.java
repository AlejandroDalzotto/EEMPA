package chinchulin.varano.Models;

import chinchulin.varano.Utils.ExamState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exam_records")
public class ExamRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_exam_record;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_exam")
    private Exam exam;

    @Enumerated(EnumType.STRING)
    private ExamState state;

    @Column(name = "attended", nullable = false)
    private Boolean attended = false;

    @Column(name = "grade", nullable = false)
    private Float grade = 0f;
}
