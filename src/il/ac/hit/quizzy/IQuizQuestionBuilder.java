package il.ac.hit.quizzy;

import java.util.List;

/** Interface for building quiz questions */
public interface IQuizQuestionBuilder {

    /** Setters */

    /** Set the title of the question */
    IQuizQuestionBuilder setTitle(String text) throws QuizException;
    /** Set the text of the question */
    IQuizQuestionBuilder setQuestion(String text) throws QuizException;

    /** Additional Methods */

    /** Add an answer option to the question */
    IQuizQuestionBuilder addAnswer(String text, boolean correct);
    /** Add multiple answer options and their correctness to the question */
    IQuizQuestionBuilder addAnswers(List<String> answerList, List<Boolean> correctList);
    /** Create the quiz question. */
    IQuizQuestion create();
}
