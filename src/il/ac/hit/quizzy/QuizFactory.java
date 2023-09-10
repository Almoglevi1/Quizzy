package il.ac.hit.quizzy;

/**
* Represents a factory for creating instances of IQuiz based on the Prototype design pattern
* The use of the clone() method in the QuizFactory class aligns with the Prototype design pattern,
* as it allows for the creation of new objects by copying existing ones, serving as prototypes
*/

public class QuizFactory {
        private final TerminalQuiz terminalQuiz;
        private final GUIQuiz guiQuiz;

        /** Constructor */
        public QuizFactory() {
            terminalQuiz = new TerminalQuiz();
            guiQuiz = new GUIQuiz();
        }

        /** Create a quiz based on the provided type */
        public IQuiz createQuiz(QuizType type) {
            if (type == QuizType.TERMINAL) {
                /** Create a new IQuiz instance by cloning the TerminalQuiz prototype */
                return (IQuiz) terminalQuiz.clone();
            } else if (type == QuizType.GUI) {
                /** Create a new IQuiz instance by cloning the GUIQuiz prototype */
                return (IQuiz) guiQuiz.clone();
            } else {
                throw new IllegalArgumentException("Invalid QuizType");
            }
        }
}
