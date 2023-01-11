package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn.ex1;

import be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn.BookstoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@Profile("!test")
@Order(1)
@RequiredArgsConstructor
public class Ex2MysqlJsonColumnStartupLoadingDataCommandLineRunner implements CommandLineRunner {

    private final Book2Repository book2Repository;

    @Override
    public void run(String... args) throws Exception {

        Book2 book2 = new Book2()
                .setIsbn("978-9730228236")
                .addProperty("title", "High-Performance Java Persistence")
                .addProperty("author", "Vlad Mihalcea")
                .addProperty("publisher", "Amazon")
                .addProperty("price", "$44.95");
        PageCustom pageCustom1 = new PageCustom();
        pageCustom1.setType("General skills");
        List<String> newList = new ArrayList<>();
        newList.add("int1");
        newList.add("int2");
        newList.add("int3");
        pageCustom1.setNames(newList);
        book2.addPageCustom(pageCustom1);

        PageCustom pageCustom2 = new PageCustom();
        pageCustom2.setType("Office skills");
        List<String> newList2 = new ArrayList<>();
        newList2.add("int1");
        newList2.add("int2");
        newList2.add("int3");
        pageCustom2.setNames(newList2);
        book2.addPageCustom(pageCustom2);

        book2Repository.save(book2);

        Book2 byIsbn = book2Repository.findByIsbn("978-9730228236");
        Map<String, String> bookProperties = byIsbn.getProperties();

        if (bookProperties.get("title").equals("High-Performance Java Persistence")) {
            log.info("title is equal");
        }

        if (bookProperties.get("author").equals("Vlad Mihalcea")) {
            log.info("title is equal");
        }

        log.info(byIsbn.getPages().toString());
        log.info(byIsbn.getPages().get(0).getType() + " - " + byIsbn.getPages().get(0).getNames().toString());


    }
}
