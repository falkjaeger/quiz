import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by falk on 17.06.16.
 */
public class QuizParser {
    public QuizList parseFile() throws FileNotFoundException {
        QuizList questionList = new QuizList();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("quiz.csv")))) {
            String s = reader.readLine();
            while (s != null) {
                String[] questionString = s.split(";");
                Question question = new Question();
                if (questionString[0].matches("[0-9]")) {
                    question.setLevel(Integer.parseInt(questionString[0]));
                    question.setQuestions(questionString[1]);
                    question.setCorrectAnswer(questionString[2]);
                    List<String> wrongAnswers = new ArrayList<>();
                    wrongAnswers.add(questionString[3]);
                    wrongAnswers.add(questionString[4]);
                    wrongAnswers.add(questionString[5]);
                    question.setWrongAnswers(wrongAnswers);
                    questionList.add(question);
                }
                s = reader.readLine();
            }
            return questionList;
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Parsen der CSV Datei!",e);
        }

    }
}
