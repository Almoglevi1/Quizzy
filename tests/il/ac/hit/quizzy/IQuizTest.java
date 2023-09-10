package il.ac.hit.quizzy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IQuizTest {
    private IQuiz quiz;

    @BeforeEach
    void setUp() {
        /** Initialize a new Quiz instance before each test */
        quiz = new TerminalQuiz();
    }

    @Test
    void setName() {
        /** Set the name for the quiz */
        String expectedName = "Sample Quiz";
        try {
            quiz.setName(expectedName);
            String actualName = quiz.getName();
            assertEquals(expectedName, actualName);
        } catch (QuizException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void getName() {
        /** Get the name of the quiz */
        String expectedName = "Sample Quiz";
        try {
            quiz.setName(expectedName);
            String actualName = quiz.getName();
            assertEquals(expectedName, actualName);
        } catch (QuizException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void addQuestion() throws QuizException {
        /** Create a sample quiz question */
        IQuizQuestion question = new QuizQuestion.Builder()
                .setTitle("Question 1")
                .setQuestion("What is 2 + 2?")
                .addAnswer("3", false)
                .addAnswer("4", true)
                .addAnswer("5", false)
                .addAnswer("6", false)
                .addAnswer("7", false)
                .create();

        /** Add the question to the quiz */
        quiz.addQuestion(question);

        /** Get the list of questions in the quiz and check if the added question is present */
        assertEquals(1, quiz.getQuestions().size());
        assertEquals(question, quiz.getQuestions().get(0));
    }

    @AfterEach
    void tearDown() {
        /** Clean up resources or perform any necessary teardown after each test */
        quiz = null;
    }
}
