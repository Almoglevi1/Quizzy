package il.ac.hit.quizzy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
* QuizQuestion class implements IQuizQuestion and inside him implements the IQuizQuestionBuilder,
* and thus we cover the tests of both
*/

class QuizQuestionTest {

    private QuizQuestion quizQuestion;

    @BeforeEach
    void setUp() throws QuizException {
        /** Initialize or create an instance of QuizQuestion for testing */
        quizQuestion = (QuizQuestion) new QuizQuestion.Builder()
                .setTitle("Question 1")
                .setQuestion("What is 2 + 2?")
                .addAnswer("3", false)
                .addAnswer("4", true)
                .addAnswer("5", false)
                .addAnswer("6", false)
                .addAnswer("7", false)
                .create();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTitle() {
        /** Test the getTitle() method */
        String expectedTitle = "Question 1";
        String actualTitle = quizQuestion.getTitle();
        assertEquals(expectedTitle, actualTitle, "Titles should match");
    }

    @Test
    void getQuestion() {
        /** Test the getQuestion() method */
        String expectedQuestion = "What is 2 + 2?";
        String actualQuestion = quizQuestion.getQuestion();
        assertEquals(expectedQuestion, actualQuestion, "Questions should match");
    }

    @Test
    void getAnswers() {
        /** Test the getAnswers() method */
        List<String> expectedAnswers = Arrays.asList(
                "3",
                "4",
                "5",
                "6",
                "7"
        );
        List<String> actualAnswers = quizQuestion.getAnswers();
        assertEquals(expectedAnswers, actualAnswers, "Answers should match");
    }

    @Test
    void getCorrectAnswers() {
        /** Test the getCorrectAnswers() method */
        List<Boolean> expectedCorrectAnswers = Arrays.asList(
                false, true, false, false, false
        );
        List<Boolean> actualCorrectAnswers = quizQuestion.getCorrectAnswers();
        assertEquals(expectedCorrectAnswers, actualCorrectAnswers, "Correct answers should match");
    }

    @Test
    void testClone() {
        /** Test the testClone() method */
        QuizQuestion clonedQuestion = (QuizQuestion) quizQuestion.clone();
        assertNotSame(quizQuestion, clonedQuestion, "Cloned question should not be the same instance");

        /** Verify that the cloned question is deep-copied */
        assertNotSame(quizQuestion.getAnswers(), clonedQuestion.getAnswers(), "Answers list should not be the same instance");
        assertNotSame(quizQuestion.getCorrectAnswers(), clonedQuestion.getCorrectAnswers(), "Correct answers list should not be the same instance");
    }
}
