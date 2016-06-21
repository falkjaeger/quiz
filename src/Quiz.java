import java.util.ArrayList;
import java.util.List;

/**
 * Created by falk on 17.06.16.
 */
public class Quiz {
    private boolean joker;
    private int level;

    public Quiz(boolean joker) {
        this.joker = joker;
    }

    /**
     * Asks the questions and provides the answers
     * @param curentQuestion
     * @param level
     * @return
     */
    public boolean askQuestion(Question curentQuestion, int level) {
        this.level = level;
        System.out.println("Die "+new Integer(1+level)*100+" Euro Frage!");
        System.out.println(curentQuestion.getQuestion());
        int positionCorrectAnswer = (int) (Math.random() * ((curentQuestion.getWrongAnswers().size()) + 1));
        curentQuestion.getWrongAnswers().add(positionCorrectAnswer, curentQuestion.getCorrectAnswer());
        for (int j = 0; j < curentQuestion.getWrongAnswers().size(); j++) {
            System.out.println("\t" + new Integer(1 + j) + ". " + curentQuestion.getWrongAnswers().get(j));
        }
        int amountOfAnswers = curentQuestion.getWrongAnswers().size();
        if (joker) {
            System.out.println("\t5. 50:50 Joker");
            amountOfAnswers = 5;

        }
        return getUserInput(amountOfAnswers, positionCorrectAnswer, curentQuestion);

    }

    /**
     * Checks if the answer is true
     * or executes the 50:50 Joker
     * @param amountOfAnswers
     * @param positionCorrectAnswer
     * @param curentQuestion
     * @return
     */
    private boolean getUserInput(int amountOfAnswers, int positionCorrectAnswer, Question curentQuestion) {
        int userAnswer = Tastatur.intInput();
        while (userAnswer < 0 || userAnswer > amountOfAnswers) {
            System.out.println("Invalide Antwort");
            userAnswer = Tastatur.intInput();
        }
        if (userAnswer == positionCorrectAnswer + 1) {
            System.out.println("Korrekt!\n");
            return true;
        } else if (joker && userAnswer == 5) {
            String wrongQuestion = curentQuestion.joker(positionCorrectAnswer);
            List<String> wrongQuestions = new ArrayList<>();
            wrongQuestions.add(wrongQuestion);
            Question question = new Question(curentQuestion.getQuestion(), 0, curentQuestion.getCorrectAnswer(), wrongQuestions);
            joker = false;
            return askQuestion(question, level);

        } else {
            System.out.println("Falsch\n");
            return false;
        }
    }
}
