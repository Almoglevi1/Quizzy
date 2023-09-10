package il.ac.hit.quizzy;

import java.io.*;
import java.util.*;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {
    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO() {
        /** Private constructor for Singleton */
    }

    /** Singleton pattern: Get the instance of SimpleCSVQuizFilesDAO */
    public static synchronized IQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    /** Helper method to extract the first element from a file */
    public static String extractFirstElement(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine().split(",", 2)[0];
        } catch (IOException e) {
            /** Handle any IOException and wrap it in a QuizException */
            throw new QuizException("Error extracting the first element: ", e);
        }
    }

    /**
    * Saves a quiz to a CSV file. This method takes a quiz object and a file name,
    * then writes the quiz data to a CSV file, including the quiz type, name, questions, and answers
    */
    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(quiz.getType() + ",");
            writer.write(quiz.getName() + ",");
            for (IQuizQuestion question : quiz.getQuestions()) {
                writer.write(question.getTitle() + ",");
                writer.write(question.getQuestion() + ",");
                List<String> answers = question.getAnswers();
                List<Boolean> correctAnswers = question.getCorrectAnswers();
                for (int i = 0; i < answers.size(); i++) {
                    writer.write(answers.get(i) + ",");
                    writer.write(correctAnswers.get(i).toString() + ",");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            /** Handle any IOException and wrap it in a QuizException */
            throw new QuizException("Error saving quiz to file: ", e);
        }
    }

    /**
    * Loads a quiz from a CSV file. This method reads quiz data from a CSV file,
    * creates a quiz object, and populates it with the retrieved information,
    * including the quiz type, name, questions, and answers
    */
    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        /** Extract quiz type (TERMINAL or GUI) from the file */
        String quizType = extractFirstElement(fileName);
        IQuiz quiz = null;
        if (quizType.equals("TERMINAL")){
            quiz = new TerminalQuiz();
        }else{
            quiz = new GUIQuiz();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean flag = true;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (flag == true){
                    /** Set the quiz name from the file */
                    quiz.setName(parts[1]);
                    String[] newArray = new String[parts.length - 2];

                    /** Adjust the parts array to skip quiz name and type */
                    for (int i = 2; i < parts.length; i++) {
                        newArray[i - 2] = parts[i];
                    }
                    parts = newArray;
                    flag = false;
                }

                if (parts.length < 5) {
                    /** Check for invalid data format in the CSV file */
                    throw new QuizException("Invalid data format in the CSV file.");
                }

                /** retrieving the information from the file */
                String title = parts[0];
                String questionText = parts[1];
                List<String> answers = new ArrayList<>();
                List<Boolean> correctAnswers = new ArrayList<>();
                for (int i = 2; i < parts.length; i += 2) {
                    answers.add(parts[i]);
                    correctAnswers.add(Boolean.parseBoolean(parts[i + 1]));
                }
                /** Enter the information to the quiz instance */
                IQuizQuestion question = new QuizQuestion.Builder()
                        .setTitle(title)
                        .setQuestion(questionText)
                        .addAnswers(answers, correctAnswers)
                        .create();
                quiz.addQuestion(question);
            }
        } catch (IOException e) {
            /** Handle any IOException and wrap it in a QuizException */
            throw new QuizException("Error loading quiz from file: ", e);
        }
        return quiz;
    }

}