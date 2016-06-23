import java.util.ArrayList;
import java.util.List;

/**
 * Created by falk on 17.06.16.
 */
public class Quiz {
    private boolean joker;
    private int level;

    public Quiz(GameMode mode) {
        joker = !mode.equals(GameMode.safety);
    }

    /**
     * Asks the questions and provides the answers
     * @param curentQuestion The current Question
     * @param level The level of the Question
     * @return True if the user answer is right or false if it is not
     */
    public boolean askQuestion(Question curentQuestion, int level) {
        this.level = level;
        System.out.println("Die "+(1+level)*100+" Euro Frage!");
        System.out.println(curentQuestion.getQuestion());
        //Sets the position of the right answer so it will be random
        int positionCorrectAnswer = (int) (Math.random() * ((curentQuestion.getWrongAnswers().size()) + 1));
        curentQuestion.getWrongAnswers().add(positionCorrectAnswer, curentQuestion.getCorrectAnswer());
        for (int j = 0; j < curentQuestion.getWrongAnswers().size(); j++) {
            System.out.println("\t" + (1 + j) + ". " + curentQuestion.getWrongAnswers().get(j));
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
     * @param amountOfAnswers The amount of possible answers the question got
     * @param positionCorrectAnswer The position of the correct answer
     * @param curentQuestion The question
     * @return true if the users answer is right, false if it is wrong
     */
    private boolean getUserInput(int amountOfAnswers, int positionCorrectAnswer, Question curentQuestion) {
        int userAnswer = Tastatur.intInput();
       //Checks if the user input is valid
        while (userAnswer < 1 || userAnswer > amountOfAnswers) {
            System.out.println("Invalide Antwort");
            userAnswer = Tastatur.intInput();
        }
        //checks if the users input is correct
        if (userAnswer == positionCorrectAnswer + 1) {
            System.out.println("Korrekt!\n");
            return true;
            // If the user uses the joker
        } else if (joker && userAnswer == 5) {
            //gets one wrong answer
            String wrongAnswer = curentQuestion.joker(positionCorrectAnswer);
            List<String> wrongAnswers = new ArrayList<>();
            wrongAnswers.add(wrongAnswer);
            //Creates a new Question Object
            Question question = new Question(curentQuestion.getQuestion(), 0, curentQuestion.getCorrectAnswer(), wrongAnswers);
            joker = false;
            //Ask the question again with only two answer possibilities
            return askQuestion(question, level);

        } else {
            System.out.println("Falsch\n");
            return false;
        }
    }
}
