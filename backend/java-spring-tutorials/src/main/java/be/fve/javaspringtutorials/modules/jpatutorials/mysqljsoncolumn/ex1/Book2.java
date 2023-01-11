package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn.ex1;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table
public class Book2 {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(length = 15)
    private String isbn;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> properties = new HashMap<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<PageCustom> pages = new ArrayList<>();

    public String getIsbn() {
        return isbn;
    }

    public Book2 setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Book2 setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public Book2 addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

    public List<PageCustom> getPages() {
        return pages;
    }

    public Book2 setPages(List<PageCustom> pages) {
        this.pages = pages;
        return this;
    }

    public Book2 addPageCustom(PageCustom pageCustom) {
        pages.add(pageCustom);
        return this;
    }
}