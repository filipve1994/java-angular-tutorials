package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public void newAuthor() {

        Book book = new Book();
        book.setIsbn("001-JN");
        book.setTitle("A History of Ancient Prague");
        book.setPrice(45);

        Author author = new Author();
        author.setName("Joana Nimar");
        author.setAge(34);
        author.setGenre("History");
        author.setBook(book);

        authorRepository.save(author);
    }

    public void byName() {
        Author author = authorRepository.findByName("Joana Nimar");
        System.out.println(author);
    }

    public void byNameIsbn() {
        Author author = authorRepository.findByBookIsbn("001-JN");
        System.out.println(author);
    }

    public void byBookIsbnNativeQuery() {
        Author author = authorRepository.findByBookIsbnNativeQuery("001-JN");
        System.out.println(author);
    }
}