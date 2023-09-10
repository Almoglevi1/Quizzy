package il.ac.hit.quizzy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTypeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void valueOf() {
        /** Test the QuizType.valueOf method */

        /** Test for a valid quiz type */
        QuizType terminalQuiz = QuizType.valueOf("TERMINAL");
        assertEquals(QuizType.TERMINAL, terminalQuiz, "Expected TERMINAL");

        /** Test for another valid quiz type */
        QuizType guiQuiz = QuizType.valueOf("GUI");
        assertEquals(QuizType.GUI, guiQuiz, "Expected GUI");

        /** Test for an invalid quiz type, it should throw an IllegalArgumentException */
        assertThrows(IllegalArgumentException.class, () -> {
            QuizType.valueOf("INVALID_TYPE");
        });
    }
}
