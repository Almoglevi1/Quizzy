import il.ac.hit.quizzy.*;
import java.util.Scanner;

public class Program {

    public static void main(String argos[]) throws QuizException {

        Scanner scanner = new Scanner(System.in);

        // Let the user choose what type of quiz he wants to create
        System.out.println("what type of quiz do you want to create? (GUI or TERMINAL)");
        String userInput = scanner.nextLine();

        // Creating a quiz based on the type the user has selected
        QuizFactory factory = new QuizFactory();
        IQuiz quiz;
        if ("GUI".equalsIgnoreCase(userInput)) {
            quiz = factory.createQuiz(QuizType.GUI);
        } else if ("TERMINAL".equalsIgnoreCase(userInput)) {
            quiz = factory.createQuiz(QuizType.TERMINAL);
        } else {
            System.out.println("Invalid input. Please enter 'GUI' or 'TERMINAL'.");
            return;
        }

        // Setting the quiz name
        quiz.setName("Quiz Demo");

        // Creating 1st question
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("We Love Canada");
        builder1.setQuestion("Canada starts with…?");
        builder1.addAnswer("Canada starts with the letter ‘A’.", false);
        builder1.addAnswer("Canada starts with the letter ‘B’.", false);
        builder1.addAnswer("Canada starts with the letter ‘C’.", true);
        builder1.addAnswer("Canada starts with the letter ‘D’.", false);
        builder1.addAnswer("Canada starts with the letter ‘E’.", false);
        IQuizQuestion question1 = builder1.create();

        // Creating 2nd question
        IQuizQuestionBuilder builder2 = new QuizQuestion.Builder();
        builder2.setTitle("We Love Australia");
        builder2.setQuestion("Australia starts with…?");
        builder2.addAnswer("Australia starts with the letter ‘A’.", true);
        builder2.addAnswer("Australia starts with the letter ‘C’.", false);
        builder2.addAnswer("Australia starts with the letter ‘Q’.", false);
        builder2.addAnswer("Australia starts with the letter ‘N’.", false);
        builder2.addAnswer("Australia starts with the letter ‘R’.", false);
        IQuizQuestion question2 = builder2.create();

        // Creating 3rd question
        IQuizQuestionBuilder builder3 = new QuizQuestion.Builder();
        builder3.setTitle("We Love Spain");
        builder3.setQuestion("Spain starts with…?");
        builder3.addAnswer("Spain starts with the letter ‘G’.", false);
        builder3.addAnswer("Spain starts with the letter ‘D’.", false);
        builder3.addAnswer("Spain starts with the letter ‘F’.", false);
        builder3.addAnswer("Spain starts with the letter ‘S’.", true);
        builder3.addAnswer("Spain starts with the letter ‘L’.", false);
        IQuizQuestion question3 = builder3.create();

        // Adding questions to quiz
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);

        // Saving quiz to file and read it back
        IQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();
        dao.saveQuizToFile(quiz, "quiz1.data");
        IQuiz loadedQuiz = dao.loadQuizFromFile("quiz1.data");
        loadedQuiz.start();
    }
}



