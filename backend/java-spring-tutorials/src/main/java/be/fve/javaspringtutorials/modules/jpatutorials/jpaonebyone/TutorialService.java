package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class TutorialService implements TutorialFacade {

//    @Autowired
    private TutorialRepository tutorialRepository;

//    @Autowired
    private TutorialDetailsRepository detailsRepository;

    //tutorials

    @Override
    public List<Tutorial> getAllTutorials(String title) {

        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) {
            log.info("getAllTutorials call! => return all tutorials");
            tutorialRepository.findAll().forEach(tutorials::add);
        } else {
            log.info("getAllTutorials call! => return all tutorials for title : " + title);
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }

        return tutorials;
    }

    @Override
    public Tutorial getTutorialById(Long id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new TutorialNotFoundException("Not found Tutorial with id = " + id));
    }

    @Override
    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
    }

    @Override
    public Tutorial updateTutorial(Long id, Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new TutorialNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        return tutorialRepository.save(_tutorial);
    }

    @Override
    public void deleteTutorial(Long id) {
        if (detailsRepository.existsById(id)) {
            detailsRepository.deleteById(id);
        }

        tutorialRepository.deleteById(id);
    }

    @Override
    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    @Override
    public List<Tutorial> findPublishedTutorials() {
        return tutorialRepository.findByPublished(true);
    }


    //details

    @Override
    public TutorialDetails getDetailsById(Long id) {

        return detailsRepository.findById(id)
                .orElseThrow(() -> new TutorialDetailsNotFoundException("Not found Tutorial Details with id = " + id));
    }

    @Override
    public Tutorial createDetails(Long tutorialId, TutorialDetails detailsRequest) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new TutorialDetailsNotFoundException("Not found Tutorial with id = " + tutorialId));

        TutorialDetails toSave = new TutorialDetails();
        toSave.setCreatedBy(detailsRequest.getCreatedBy());
        toSave.setCreatedOn(LocalDate.now());

        tutorial.setTutorialDetails(toSave);

        toSave.setTutorial(tutorial);

        Tutorial saved = tutorialRepository.save(tutorial);

        return saved;
    }

    @Override
    public TutorialDetails updateDetails(Long id, TutorialDetails detailsRequest) {
        TutorialDetails details = detailsRepository.findById(id)
                .orElseThrow(() -> new TutorialDetailsNotFoundException("Id " + id + " not found"));

        details.setCreatedBy(detailsRequest.getCreatedBy());

        return detailsRepository.save(details);
    }

    @Override
    public void deleteDetails(Long id){
        detailsRepository.deleteById(id);
    }

    @Override
    public void deleteDetailsOfTutorial(Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new TutorialNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        detailsRepository.deleteByTutorialId(tutorialId);
    }

}
