import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by falk on 17.06.16.
 */
public class QuizList extends ArrayList {
    public Question getQuestion(int level){
        Collections.shuffle(this);
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) instanceof Question){
                if (((Question) this.get(i)).getLevel() == level)
                    return (Question) this.get(i);
            }
        }
    return null;
    }
}
