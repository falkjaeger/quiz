import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by falk on 17.06.16.
 */
public class Main {
    public static void main(String[] args)  {
        //If the User got an Joker joker will be true
        boolean joker;
        //The level pf the current Question
        int level = 1;
        QuizParser parser = new QuizParser();
        QuizList questionList;
        try {
            //Parse the File with Questions
            questionList = parser.parseFile();
        } catch (Exception e) {
            throw new RuntimeException("Failed Parsinf File",e);

        }
        System.out.println("Welchen Modus möchtest du spielen? \n" +
                "1 für die SicherheitsVariante \n" +
                "2 für die ZockerVariante");
        int modus = Tastatur.intInput();
        //Checks if user Input is correct
        while (modus < 0 || modus > 2) {
            System.out.println("Falsche eingabe!");
            modus = Tastatur.intInput();
        }
        //Sets the modus for the game
        if (modus == 1) {
            joker = false;
        } else {
            joker = true;
        }
        Quiz quiz = new Quiz(joker);
        //Ask 5 Random Questions 1 Question per Level
        for (int i = 0; i < 5; i++) {
            Question curentQuestion = questionList.getQuestion(level);
            if (quiz.askQuestion(curentQuestion,i)){
                level++;
            }else {
                 if(!joker && level >=4) {
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
