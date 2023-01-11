package be.fve.javaspringtutorials.modules.jpatutorials.jpaonetomany;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Log4j2
@Profile("!test")
public class JpaOneToManyDemoLoadingDataCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        // https://www.baeldung.com/java-faker
        Faker faker = new Faker();

        Random randomGenerator = new Random();
        for (int i = 1; i <= 10; i++) {
            Post postToSave = new Post();
            postToSave.setTitle("Post " + i);
            postToSave.setDescription("Post " + i + " description");
            postToSave.setContent("Post " + i + " content");
            postRepository.save(postToSave);
        }

        List<Post> all = postRepository.findAll();

        for (int i = 0; i < all.size(); i++) {
            Post post = all.get(i);
            // https://java2blog.com/java-random-number-1-10/
            int randomCount = randomGenerator.nextInt(10) + 1;

            System.out.println(randomCount);

            for (int j = 0; j < randomCount; j++) {
                Comment comment = new Comment();
//                comment.setText("Great Post Comment " + j);
                comment.setText(faker.lorem().sentence(10));
                comment.setPost(post);
                commentRepository.save(comment);

            }

        }

    }

}
