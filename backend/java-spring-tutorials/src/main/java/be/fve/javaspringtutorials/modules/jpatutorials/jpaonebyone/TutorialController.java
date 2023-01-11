package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/example")
//@RequestMapping(value = "/api/example/1")
@Tag(name = "tutorialsonebyone", description = "the tutorials jpa onebyone example API")
@Slf4j
public class TutorialController {

    @Autowired
    private TutorialFacade facade;

    @Operation(summary = "Find all tutorials", description = "", tags = {"tutorialsonebyone"})
    @GetMapping(value = "/tutorials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        log.info("getAllTutorials call!");
        List<Tutorial> tutorials = facade.getAllTutorials(title);

        if (tutorials.isEmpty()) {
            log.info("getAllTutorials call! => there are no tutorials : ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @Operation(summary = "retrieve Details by :id", description = "", tags = {"tutorialsonebyone"})
    @GetMapping(value = "/tutorials/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTutorialById(@PathVariable("id") long id) {
        log.info("getTutorialById call!");

        Tutorial tutorial = facade.getTutorialById(id);

        log.info("getTutorialById call! => tutorial is : " + tutorial);

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @Operation(summary = "create new tutorial", description = "", tags = {"tutorialsonebyone"})
    @PostMapping(value = "/tutorials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTutorial(@RequestBody Tutorial tutorial) {
        log.info("createTutorial call!");

        log.info("createTutorial call! => tutorial param is : " + tutorial);

        Tutorial _tutorial = facade.createTutorial(tutorial);
        log.info("createTutorial call! => tutorial saved is : " + _tutorial);

        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @Operation(summary = "update a specific tutorial", description = "", tags = {"tutorialsonebyone"})
    @PutMapping(value = "/tutorials/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        log.info("updateTutorial call!");

        return new ResponseEntity<>(facade.updateTutorial(id, tutorial), HttpStatus.OK);
    }

    @Operation(summary = "delete a Tutorial (and its Details)", description = "", tags = {"tutorialsonebyone"})
    @DeleteMapping(value = "/tutorials/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        log.info("deleteTutorial call!");

        facade.deleteTutorial(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "delete all tutorials", description = "", tags = {"tutorialsonebyone"})
    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        log.info("deleteAllTutorials call!");

        facade.deleteAllTutorials();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "find all published tutorials", description = "", tags = {"tutorialsonebyone"})
    @GetMapping(value = "/tutorials/published", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tutorial>> findByPublished() {
        log.info("findByPublished call!");

        List<Tutorial> tutorials = facade.findPublishedTutorials();

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}
