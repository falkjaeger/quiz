import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by falk on 17.06.16.
 */
public class QuizParser {
    /**
     * Parse the Xml File with the Questions and Create an QuizList object
     * @return the from the xml generated QuizList
     * @throws FileNotFoundException
     * @throws JAXBException
     */
    public QuizList parseFile() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(QuizList.class);
        Unmarshaller un = context.createUnmarshaller();
        QuizList quizList = (QuizList) un.unmarshal(getClass().getResourceAsStream("quiz.xml"));
        return quizList;
    }
}
