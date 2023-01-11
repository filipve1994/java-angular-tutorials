package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);


}
