package be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn.ex1;

import be.fve.javaspringtutorials.modules.jpatutorials.mysqljsoncolumn.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Book2Repository extends JpaRepository<Book2, Long> {

    Book2 findByIsbn(String id);

}