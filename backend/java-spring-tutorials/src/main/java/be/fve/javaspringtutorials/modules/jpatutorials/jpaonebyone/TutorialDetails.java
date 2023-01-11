package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "tutorial_details")
@Getter
@Setter
//@ToString
//@EntityListeners(AuditingEntityListener.class)
//@NoArgsConstructor
//@AllArgsConstructor
public class TutorialDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutorial_id")
    private Long id;

    @Column
    private LocalDate createdOn;

    @Column
    @Schema(example = "bezKoder")
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tutorial_id", nullable = false)
    @JsonIgnore
    private Tutorial tutorial;

    public TutorialDetails() {
    }

    public TutorialDetails(String createdBy) {
//        this.createdOn = new Date();
        this.createdOn = LocalDate.now();
        this.createdBy = createdBy;
    }
}
