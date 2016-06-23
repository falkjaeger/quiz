import javax.xml.bind.JAXBException;
import javax.xml.ws.Service;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by falk on 17.06.16.
 */
public class Main {
    public static void main(String[] args)  {
        GameMode mode;
        //If the User got a Joker joker will be true
        boolean joker;
        //The level of the current Question
        int level = 1;
        QuizParser parser = new QuizParser();
        QuizList questionList;
        try {
            //Parse the File with Questions
            questionList = parser.parseFile();
        } catch (Exception e) {
            throw new RuntimeException("Parsing Questions File failed",e);

        }
        System.out.println("Welchen Modus möchtest du spielen? ");
        for (int i = 0; i < GameMode.values().length; i++) {
            System.out.println((i+1)+" "+GameMode.values()[i].toString());
        }
        int modus = Tastatur.intInput();
        //Checks if user Input is correct
        while (modus < 1 || modus > GameMode.values().length) {
            System.out.println("Falsche eingabe!");
            modus = Tastatur.intInput();
        }
        //Sets the modus for the game
        mode=GameMode.values()[modus-1];
        Quiz quiz = new Quiz(mode);
        //Ask 5 Random Questions 1 Question per Level
        for (int i = 0; i < 5; i++) {
            Question currentQuestion = questionList.getQuestion(level);
            if (quiz.askQuestion(currentQuestion,i)){
                level++;
            }else {
                 if(mode.equals(GameMode.safety) && level >=4) {
                    System.out.println("Du hast 300 Euro gewonnen");
                    System.exit(1);
                }else{
                     System.out.println("Du hast leider Verloren!");
                     System.exit(1);
                 }
            }

        }
        System.out.println("Glückwunsch du hast gewonnen!");
    }
}
