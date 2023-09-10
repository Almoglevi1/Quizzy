package il.ac.hit.quizzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IQuizFilesDAOTest {
    private IQuizFilesDAO quizFilesDAO;
    private String tempFileName;

    @BeforeEach
    void setUp() {
        quizFilesDAO = SimpleCSVQuizFilesDAO.getInstance();
        /** Create a temporary file name for testing */
        tempFileName = "temp_quiz_file.csv";
    }

    @AfterEach
    void tearDown() {
        /** Delete the temporary file if it exists */
        File tempFile = new File(tempFileName);
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void saveQuizToFile() {
        /** Create a sample quiz and add questions to it */
        IQuiz quiz = new TerminalQuiz();
        try {
            quiz.setName("Sample Quiz");
            List<IQuizQuestion> questions = new ArrayList<>();
            IQuizQuestion question1 = new QuizQuestion.Builder()
                    .setTitle("Question 1")
                    .setQuestion("What is 2 + 2?")
                    .addAnswer("3", false)
                    .addAnswer("4", true)
                    .addAnswer("5", false)
                    .addAnswer("6", false)
                    .addAnswer("7", false)
                    .create();
            questions.add(question1);
            quiz.setQuestions(questions);

            /** Save the quiz to a temporary file */
            quizFilesDAO.saveQuizToFile(quiz, tempFileName);

            /** Verify that the file exists */
            File tempFile = new File(tempFileName);
            assertTrue(tempFile.exists());
        } catch (QuizException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void loadQuizFromFile() {
        /** Create a sample quiz and add questions to it */
        IQuiz quiz = new TerminalQuiz();
        try {
            quiz.setName("Sample Quiz");
            List<IQuizQuestion> questions = new ArrayList<>();
            IQuizQuestion question1 = new QuizQuestion.Builder()
                    .setTitle("Question 1")
                    .setQuestion("What is 2 + 2?")
                    .addAnswer("3", false)
                    .addAnswer("4", true)
                    .addAnswer("5", false)
                    .addAnswer("6", false)
                    .addAnswer("7", false)
                    .create();
            questions.add(question1);
            quiz.setQuestions(questions);

            /** Save the quiz to a temporary file */
            quizFilesDAO.saveQuizToFile(quiz, tempFileName);

            /** Load the quiz from the temporary file */
            IQuiz loadedQuiz = quizFilesDAO.loadQuizFromFile(tempFileName);

            /** Verify that the loaded quiz has the same name and questions as the original quiz */
            assertEquals(quiz.getName(), loadedQuiz.getName());
            assertEquals(quiz.getQuestions().size(), loadedQuiz.getQuestions().size());
            assertEquals(quiz.getQuestions().get(0).getTitle(), loadedQuiz.getQuestions().get(0).getTitle());
        } catch (QuizException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
