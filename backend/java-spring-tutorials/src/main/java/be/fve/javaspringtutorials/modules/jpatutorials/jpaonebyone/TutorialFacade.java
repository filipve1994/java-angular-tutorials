package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import java.util.List;

public interface TutorialFacade {
    List<Tutorial> getAllTutorials(String title);

    Tutorial getTutorialById(Long id);

    Tutorial createTutorial(Tutorial tutorial);

    Tutorial updateTutorial(Long id, Tutorial tutorial);

    void deleteTutorial(Long id);

    void deleteAllTutorials();

    List<Tutorial> findPublishedTutorials();

    TutorialDetails getDetailsById(Long id);

    Tutorial createDetails(Long tutorialId, TutorialDetails detailsRequest);

    TutorialDetails updateDetails(Long id, TutorialDetails detailsRequest);

    void deleteDetails(Long id);

    void deleteDetailsOfTutorial(Long tutorialId);
}
