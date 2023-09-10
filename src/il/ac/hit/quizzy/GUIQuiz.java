package il.ac.hit.quizzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIQuiz implements IQuiz, Cloneable {
    private String name;
    private String quizType = "GUI";
    private List<IQuizQuestion> questions;

    /** Constructor for GUIQuiz */
    public GUIQuiz() {
        questions = new ArrayList<>();
    }

    /** Start the graphical user interface (GUI) quiz */
    @Override
    public void start() {
        /** Create the main JFrame for the quiz */
        JFrame frame = new JFrame("Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Create a JPanel to hold the quiz components */
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        /** Create a label for the quiz title */
        JLabel titleLabel = new JLabel("Welcome to the Quiz: " + name);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        /** Iterate through quiz questions and create UI components for each question */
        for (IQuizQuestion question : questions) {
            JLabel questionLabel = new JLabel(question.getTitle());
            questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(questionLabel);

            JLabel questionTextLabel = new JLabel(question.getQuestion());
            questionTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(questionTextLabel);

            List<String> answers = question.getAnswers();
            ButtonGroup buttonGroup = new ButtonGroup();
            for (String answer : answers) {
                JRadioButton radioButton = new JRadioButton(answer);
                radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonGroup.add(radioButton);
                panel.add(radioButton);
            }
        }

        /** Create a submit button to calculate the score */
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(submitButton);

        /** Define action when the submit button is pressed */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int score = 0;

                /** Iterate through the questions and their selected answers */
                for (int i = 0; i < questions.size(); i++) {
                    IQuizQuestion question = questions.get(i);
                    List<Boolean> correctAnswers = question.getCorrectAnswers();
                    /** Find the index of the correct answer */
                    int correctAnswerIndex = correctAnswers.indexOf(true);

                    /** Get the UI components for the current question */
                    Component[] components = panel.getComponents();
                    /** Create a new array containing components from index 1 and above, without the quiz name */
                    Component[] newComponents = Arrays.copyOfRange(components, 1, components.length);
                    int selectedAnswerIndex = -1;

                    /** Find the selected answer for the current question */
                    /** 7 components per question (title, text, 5 radio buttons) */
                    for (int j = i * 7; j < (i + 1) * 7; j++) {
                        if (newComponents[j] instanceof JRadioButton) {
                            JRadioButton radioButton = (JRadioButton) newComponents[j];
                            if (radioButton.isSelected()) {
                                /** Offset for title and question text labels */
                                selectedAnswerIndex = j - (i * 7) - 2;
                                break;
                            }
                        }
                    }

                    /** Check if the selected answer is correct and update the score */
                    if (selectedAnswerIndex == correctAnswerIndex) {
                                score++;
                    }
                }

                /** Display a message box with the quiz completion message and the user's score */
                JOptionPane.showMessageDialog(frame, "Quiz completed. Your score: " + score);
            }
        });

        /** Add the panel to the frame and display the GUI */
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
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

    /** Clone the GUIQuiz object */
    @Override
    public Object clone() {
        try {
            /** Create a new GUIQuiz instance */
            GUIQuiz clonedQuiz = (GUIQuiz) super.clone();

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
