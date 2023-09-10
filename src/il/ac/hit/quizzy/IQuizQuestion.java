package il.ac.hit.quizzy;

import java.util.List;

/** Interface representing a quiz question */
public interface IQuizQuestion {

    /** Getters */

    /** Get the title of the question */
    String getTitle();
    /** Get the text of the question */
    String getQuestion();
    /** Get the list of answer options */
    List<String> getAnswers();
    /** Get the list of correct answers (as boolean values) */
    List<Boolean> getCorrectAnswers();

    /** Additional Method */

    /** Clone the question */
    Object clone() ;
}
