package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "tutorials")
@Getter
@Setter
//@ToString
//@EntityListeners(AuditingEntityListener.class)
//@NoArgsConstructor
//@AllArgsConstructor
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @Schema(example = "bezKoder Tut#1")
    private String title;

    @Column(name = "description")
    @Schema(example = "Tut#1 descriptionexample")
    private String description;

    @Column(name = "published")
    @Schema(example = "true")
    private boolean published;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "tutorial")
    private TutorialDetails tutorialDetails;


    public Tutorial() {

    }

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
}
