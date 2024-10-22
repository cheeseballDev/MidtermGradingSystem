package MidtermGradingSystem;

import java.util.Scanner;

public class Grades implements Runnable {
    private double examScore = 0;
    private double quizScore = 0;
    private double essayScore = 0;

    public void getType() {
        clearScreen();
        Scanner userInput = new Scanner(System.in);
        Exam examObject = new Exam(0, false);
        Quiz quizObject = new Quiz(0, false);
        Essay essayObject = new Essay(0, false);
        while (true) {
            if(examObject.isFinished() && quizObject.isFinished() && essayObject.isFinished()){
                break;
            }
            System.out.println("Please select of the following: 'EXAM' 'QUIZ' 'ESSAY'");
            String choice = userInput.nextLine().toUpperCase().trim();
            getGradeType(userInput, choice, examObject, quizObject, essayObject);
        }
        addTotal("FINISH", 0, 0);
    }

    private void getGradeType(Scanner userInput, String choice, Exam examObject, Quiz quizObject ,Essay essayObject) {
        switch (choice) {
            case "EXAM":
                getExamInfo(userInput, examObject);
                break;
            case "QUIZ":
                getQuizInfo(userInput, quizObject);
                break;
            case "ESSAY":
                getEssayInfo(userInput, essayObject);
                break;
            default:
                printErrorInvalidUserType();
        }
    }

    /*
     * EXAM
     */

    private void getExamInfo(Scanner userInput, Exam examObject) {
        if (examObject.isFinished()) {
            printErrorAlreadyFinished();
        } else {
            while (true) {
                System.out.println("Please enter the title, the score you got, and the total score of the exam.");
                    String title = userInput.nextLine().trim();
                    String score = userInput.nextLine().trim();
                    String totalScore = userInput.nextLine().trim();
                if (title.isEmpty() || score.isEmpty() || totalScore.isEmpty()) {
                    printErrorEmpty();
                    continue;
                }
                
                if (!score.matches("[0-9]*\\.?[0-9]+") || !totalScore.matches("[0-9]*\\.?[0-9]+")) {
                    printErrorInvalidConversion();
                    continue;
                }
                    double scoreFinal = Double.parseDouble(score);
                    double totalScoreFinal = Double.parseDouble(totalScore);
                if (scoreFinal > totalScoreFinal) {
                    printErrorScoreIsLowerThanTotal();
                    continue;
                }
                    examObject.setTitle(title);
                    examObject.setScore(scoreFinal);
                    examObject.setTotalScore(totalScoreFinal);
                    displayExamInfo(examObject);
                    addTotal("EXAM",scoreFinal, totalScoreFinal);
                    break;
                    
            }
            examObject.setStatus(true);
        }
    }

    private void displayExamInfo(Exam examObject) {
        System.out.println("Your exam, titled '" + examObject.getTitle() +"' is scored at " + examObject.getScore() + " out of " + examObject.getTotalScore());
    }

    /*
     * ESSAY
     */

    private void getEssayInfo(Scanner userInput, Essay essayObject) {
        if (essayObject.isFinished()) {
            printErrorAlreadyFinished();
        } else {
            while (true) {
                System.out.println("Please enter the title, the score you got, and the total score of the essay.");
                    String title = userInput.nextLine();
                    String score = userInput.nextLine();
                    String totalScore = userInput.nextLine();
                if (!score.matches("[0-9]*\\.?[0-9]+") || !totalScore.matches("[0-9]*\\.?[0-9]+")) {
                    printErrorInvalidConversion();
                    continue;
                }
                    double scoreFinal = Double.parseDouble(score);
                    double totalScoreFinal = Double.parseDouble(totalScore);
                if (scoreFinal > totalScoreFinal) {
                    printErrorScoreIsLowerThanTotal();
                    continue;
                }
                    essayObject.setTitle(title);
                    essayObject.setScore(scoreFinal);
                    essayObject.setTotalScore(totalScoreFinal);
                    displayEssayInfo(essayObject);
                    addTotal("ESSAY",scoreFinal, totalScoreFinal);
                    break;
                    
            }
            essayObject.setStatus(true);
        }
    }

    private void displayEssayInfo(Essay essayObject) {
        System.out.println("Your essay, titled '" + essayObject.getTitle() +"' is scored at " + essayObject.getScore() + " out of " + essayObject.getTotalScore());
    }

    /*
     * QUIZ
     */

    private void getQuizInfo(Scanner userInput, Quiz quizObject) {
        if (quizObject.isFinished()) {
            printErrorAlreadyFinished();
        } else {
            while (true) {
                System.out.println("Please enter the title, the score you got, and the total score of the quiz.");
                    String title = userInput.nextLine();
                    String score = userInput.nextLine();
                    String totalScore = userInput.nextLine();
                if (title.isEmpty() || score.isEmpty() || totalScore.isEmpty()) {
                    printErrorEmpty();
                    continue;
                }
                if (!score.matches("[0-9]*\\.?[0-9]+") || !totalScore.matches("[0-9]*\\.?[0-9]+")) {
                    printErrorInvalidConversion();
                    continue;
                }
                    double scoreFinal = Double.parseDouble(score);
                    double totalScoreFinal = Double.parseDouble(totalScore);
                if (scoreFinal > totalScoreFinal) {
                    printErrorScoreIsLowerThanTotal();
                    continue;
                }
                    quizObject.setTitle(title);
                    quizObject.setScore(scoreFinal);
                    quizObject.setTotalScore(totalScoreFinal);
                    displayQuizInfo(quizObject);
                    addTotal("QUIZ",scoreFinal, totalScoreFinal);
                    break;
                    
            }
            quizObject.setStatus(true);
        }
    }

    private void displayQuizInfo(Quiz quizObject) {
        System.out.println("Your quiz, titled '" + quizObject.getTitle() +"' is scored at " + quizObject.getScore() + " out of " + quizObject.getTotalScore());
    }

    /*
     * COMPUTE
     */

    private void addTotal(String type, double scoreFinal, double totalScoreFinal) {
        if (type.equals("EXAM")) {
            examScore = (scoreFinal / totalScoreFinal) * 0.5;
        }
        if (type.equals("QUIZ")) {
            quizScore = (scoreFinal / totalScoreFinal)* 0.20;
        }
        if (type.equals("ESSAY")) {
            essayScore = (scoreFinal / totalScoreFinal) * 0.30;
        }
        if (type.equals("FINISH")) {
            double totalScore = (examScore + quizScore + essayScore) * 100;
            System.out.printf("The total grade of your midterms is %.2f", totalScore);
            System.exit(0);
        }
    }
    
    /*
      * EXTERNAL
      */

    private void printErrorInvalidConversion() {
        clearScreen();
        System.out.print("Invalid number, please try again!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            run();
        }
        clearScreen();
    }
    
    private void printErrorInvalidUserType() {
        clearScreen();
        System.out.print("Invalid type, please try again!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            run();
        }
        clearScreen();
    }

    private void printErrorEmpty() {
        clearScreen();
        System.out.print("One value is empty, please try again!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            run();
        }
        clearScreen();
    }

    private void printErrorAlreadyFinished() {
        clearScreen();
        System.out.print("That type is already finished, select another one instead!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            run();
        }
        clearScreen();
    }
    private void printErrorScoreIsLowerThanTotal() {
        clearScreen();
        System.out.print("User score is lower than total score of the exam, please try again!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            run();
        }
        clearScreen();
    }

    public void run() {
        try {
            Thread.sleep(350);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
