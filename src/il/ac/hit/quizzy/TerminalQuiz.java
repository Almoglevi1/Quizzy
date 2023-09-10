package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz, Cloneable {
    private String name;
    private String quizType = "TERMINAL";
    private List<IQuizQuestion> questions;

    /** Constructor for TerminalQuiz */
    public TerminalQuiz() {
        questions = new ArrayList<>();
    }

    /** Start the quiz */
    @Override
    public void start() {
        int score = 0;
        Scanner scanner = new Scanner(System.in);

        /** Display a welcome message with the quiz name */
        System.out.println("Welcome to the Quiz: " + name);

        /** Iterate through quiz questions */
        for (IQuizQuestion question : questions) {
            /** Display question title and text */
            System.out.println(question.getTitle());
            System.out.println(question.getQuestion());

            /** Display answer options */
            List<String> answers = question.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i));
            }

            /** Prompt the user for their answer */
            System.out.print("Enter your answer number: ");
            int userAnswer = scanner.nextInt();

            /** Check if the user's answer is correct and update the score */
            if (question.getCorrectAnswers().get(userAnswer - 1)) {
                score++;
            }
        }

        /** Display the quiz completion message with the user's score */
        System.out.println("Quiz completed. Your score: " + score);
    }

    /** Setters */

    /** Set the name of the quiz */
    @Override
    public void setName(String text) throws QuizException {
        if (text == null || text.isEmpty()) {
            throw new QuizException("Quiz name cannot be null or empty");
        }
        this.name = text;
    }

    /** Set the questions for the quiz */
    @Override
    public void setQuestions(List<IQuizQuestion> questions) throws QuizException {
        if (questions == null) {
            throw new QuizException("Quiz questions cannot be null");
        }
        this.questions = new ArrayList<>(questions);
    }

    /** Getters */

    /** Get the name of the quiz */
    @Override
    public String getName() {
        return name;
    }
    /** Get a list of questions in the quiz */
    @Override
    public List<IQuizQuestion> getQuestions() {
        return new ArrayList<>(questions);
    }
    /** Get the type of the quiz */
    @Override
    public String getType() {
        return this.quizType;
    }

    /** Additional Methods */

    /** Add a question to the quiz */
    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    /** Clone the TerminalQuiz object */
    @Override
    public Object clone() {
        try {
            /** Create a new TerminalQuiz instance */
            TerminalQuiz clonedQuiz = (TerminalQuiz) super.clone();

            /** Clone the list of questions deeply */
            List<IQuizQuestion> clonedQuestions = new ArrayList<>();
            for (IQuizQuestion question : questions) {
                clonedQuestions.add((IQuizQuestion) question.clone());
            }

            /** Adding the cloned questions to the cloned quiz */
            try {
                clonedQuiz.setQuestions(clonedQuestions);
            } catch (QuizException e) {
                throw new RuntimeException(e);
            }

            /** Copy the name and quizType fields */
            clonedQuiz.name = this.name;
            clonedQuiz.quizType = this.quizType;

            return clonedQuiz;

        } catch (CloneNotSupportedException e) {
            try {
                /** Handle the CloneNotSupportedException by wrapping it in a custom QuizException */
                throw new QuizException("CloneNotSupportedException while cloning", e);
            } catch (QuizException ex) {
                /** If an error occurs while handling the CloneNotSupportedException, wrap it in a RuntimeException for further processing */
                throw new RuntimeException(ex);
            }
        }
    }
}



