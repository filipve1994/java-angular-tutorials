package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn.ex1;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageCustom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private List<String> names = new ArrayList<>();

}