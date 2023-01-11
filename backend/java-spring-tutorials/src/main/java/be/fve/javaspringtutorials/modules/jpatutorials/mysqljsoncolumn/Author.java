package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn;

import io.hypersistence.utils.hibernate.type.json.JsonStringType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@Data
@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private int age;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Book book;
}