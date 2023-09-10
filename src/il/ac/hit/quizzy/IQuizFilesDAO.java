package il.ac.hit.quizzy;

/** Interface for data access objects (DAOs) responsible for managing quiz files */
public interface IQuizFilesDAO {
    /** Save a quiz to a file */
    public abstract void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException;
    /** Load a quiz from a file */
    public abstract IQuiz loadQuizFromFile(String fileName) throws QuizException;
}
