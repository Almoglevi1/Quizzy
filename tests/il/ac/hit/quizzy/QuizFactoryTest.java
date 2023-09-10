package il.ac.hit.quizzy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizFactoryTest {

    private QuizFactory quizFactory;

    @BeforeEach
    void setUp() {
        /** Initialize the QuizFactory instance */
        quizFactory = new QuizFactory();
    }

    @AfterEach
    void tearDown() {
        /** Clean up any resources, if needed */
        quizFactory = null;
    }

    @Test
    void createQuiz() {
        /** Create a quiz using the QuizFactory */
        IQuiz quiz = quizFactory.createQuiz(QuizType.TERMINAL);

        /** Perform assertions to check if the quiz is created successfully */
        assertNotNull(quiz, "Quiz should not be null");
    }
}
