import java.util.List;

/**
 * An Object that holds
 *  The Question
 *  The Correct answer
 *  The level (difficulty) of the Question
 *  And the wrong Answers
 * Created by falk on 17.06.16.
 */
public class Question {
    String questions;
    int level;
    String correctAnswer;
    List<String> wrongAnswers;


    public Question(String questions, int level, String correctAnswer, List<String> wrongAnswers) {
        this.questions = questions;
        this.level = level;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public Question() {

    }

    public String getQuestion() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public String joker(int positionRightAnswer) {
        int wrongQuestion = (int) (Math.random() * ((wrongAnswers.size()-1) + 1));
        while (wrongQuestion == positionRightAnswer){
            wrongQuestion = (int) (Math.random() * ((wrongAnswers.size()) + 1));
        }
        return wrongAnswers.get(wrongQuestion);
    }
}
