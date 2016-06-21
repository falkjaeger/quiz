import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by falk on 17.06.16.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        boolean joker;
        int level = 1;
        QuizParser parser = new QuizParser();
        QuizList questionList = parser.parseFile();
        System.out.println("Welchen Modus möchtest du spielen? \n" +
                "1 für die SicherheitsVariante \n" +
                "2 für die ZockerVariante");
        int modus = Tastatur.intInput();
        while (modus < 0 || modus > 2) {
            System.out.println("Falsche eingabe!");
            modus = Tastatur.intInput();
        }
        if (modus == 1) {
            joker = false;
        } else {
            joker = true;
        }
        Quiz quiz = new Quiz(joker);
        for (int i = 0; i < 5; i++) {
            Question curentQuestion = questionList.getQuestion(level);
            if (quiz.askQuestion(curentQuestion,i)){
                level++;
            }else {
                 if(!joker && level >=4) {
                    System.out.println("Du hast "+level*100+" Euro gewonnen");
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
