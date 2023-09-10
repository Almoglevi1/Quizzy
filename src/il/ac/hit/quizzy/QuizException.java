package il.ac.hit.quizzy;

import java.io.IOException;

/** Custom exception class for quiz-related exceptions */
public class QuizException extends Exception {

    private IOException ioException;
    private CloneNotSupportedException cloneNotSupportedException;

    /** Constructors */
    public QuizException(String message) {
        super(message);
    }

    /** Thrown when an input or output operation encounters an error or fails for some reason */
    public QuizException(String message, IOException ioException) {
        super(message);
        setIoException(ioException);
    }

    /** Thrown when an attempt to clone an object is made, but the object's class does not implement the Cloneable interface */
    public QuizException(String message, CloneNotSupportedException cloneNotSupportedException) {
        super(message);
        setCloneNotSupportedException(cloneNotSupportedException);
    }

    /** Setters */

    public void setIoException(IOException ioException) {
        if (ioException == null) {
            throw new IllegalArgumentException("IOException cannot be null.");
        }
        this.ioException = ioException;
    }

    public void setCloneNotSupportedException(CloneNotSupportedException cloneNotSupportedException) {
        if (cloneNotSupportedException == null) {
            throw new IllegalArgumentException("CloneNotSupportedException cannot be null.");
        }
        this.cloneNotSupportedException = cloneNotSupportedException;
    }


    /** Getters */

    public IOException getIOException() {
        return ioException;
    }

    public CloneNotSupportedException getCloneNotSupportedException() {
        return cloneNotSupportedException;
    }
}
