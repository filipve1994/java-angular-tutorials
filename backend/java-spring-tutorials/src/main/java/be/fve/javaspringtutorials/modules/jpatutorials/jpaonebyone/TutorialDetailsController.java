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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/example")
//@Tag(name = "tutorialsonebyone", description = "the tutorials jpa onebyone example API")
@Tag(name = "tutorialsdetailsonebyone", description = "the tutorials jpa onebyone example API")
@Slf4j
public class TutorialDetailsController {

    @Autowired
    private TutorialFacade facade;

    @Operation(summary = "retrieve Details by :id or retrieve Details of a Tutorial", description = "", tags = {"tutorialsonebyone"})
    @GetMapping(value = {"/details/{id}", "/tutorials/{id}/details"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id) {
        log.info("getDetailsById call!");

        TutorialDetails details = facade.getDetailsById(id);

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @Operation(summary = "create new Details for a Tutorial", description = "", tags = {"tutorialsonebyone"})
    @PostMapping(value = "/tutorials/{tutorialId}/details", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
                                                         @RequestBody TutorialDetails detailsRequest) {
        log.info("createDetails call!");

        Tutorial saved = facade.createDetails(tutorialId, detailsRequest);

        return new ResponseEntity<>(saved.getTutorialDetails(), HttpStatus.CREATED);
    }

    @Operation(summary = "update Details by :id", description = "", tags = {"tutorialsonebyone"})
    @PutMapping(value = "/details/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TutorialDetails> updateDetails(@PathVariable("id") long id,
                                                         @RequestBody TutorialDetails detailsRequest) {
        log.info("updateDetails call!");

        return new ResponseEntity<>(facade.updateDetails(id, detailsRequest), HttpStatus.OK);
    }

    @Operation(summary = "delete Details by :id", description = "", tags = {"tutorialsonebyone"})
    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id) {
        log.info("deleteDetails call!");

        facade.deleteDetails(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "delete Details of a Tutorial", description = "", tags = {"tutorialsonebyone"})
    @DeleteMapping(value = "/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> deleteDetailsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        log.info("deleteDetailsOfTutorial call!");

        facade.deleteDetailsOfTutorial(tutorialId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
