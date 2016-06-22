import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by falk on 17.06.16.
 */
//The model for parsing the xml
// Got a list with question Objects
@XmlRootElement
public class QuizList {
private List<Question> questions= new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Returns a question with a specific level
     * @param level the level you want a question for
     * @return the question with the searched level
     */
    public Question getQuestion(int level){
        Collections.shuffle(questions);
        for (Question question : questions) {
            if (question.getLevel() == level)
                return question;
        }
    return null;
    }
}
