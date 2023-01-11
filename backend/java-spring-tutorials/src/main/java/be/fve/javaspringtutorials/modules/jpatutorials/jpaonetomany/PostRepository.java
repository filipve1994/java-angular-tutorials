package be.fve.javaspringtutorials.modules.jpatutorials.jpaonetomany;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}