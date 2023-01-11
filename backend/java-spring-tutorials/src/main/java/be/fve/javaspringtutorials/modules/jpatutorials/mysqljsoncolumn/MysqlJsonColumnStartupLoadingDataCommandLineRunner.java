package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("!test")
@Order(1)
@RequiredArgsConstructor
public class MysqlJsonColumnStartupLoadingDataCommandLineRunner implements CommandLineRunner {

    private final BookstoreService bookstoreService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n\nPersist a new author:");
        bookstoreService.newAuthor();

        System.out.println("\n\nFind author by name:");
        bookstoreService.byName();

        System.out.println("\n\nFind author by the isbn of his book:");
        bookstoreService.byNameIsbn();

        System.out.println("\n\nFind author by the isbn of his book via a native query:");
        bookstoreService.byBookIsbnNativeQuery();

    }
}
