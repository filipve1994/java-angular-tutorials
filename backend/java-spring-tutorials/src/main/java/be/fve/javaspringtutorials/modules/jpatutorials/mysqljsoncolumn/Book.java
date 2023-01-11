package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String isbn;
    private int price;
}