package MidtermGradingSystem;

import java.util.Scanner;

public class Grades implements Runnable {
    public void getType() {
        Scanner userInput = new Scanner(System.in);
        // add if statement to check if all processes are done.
        Exam examStatus = new Exam();
        Quiz quizStatus = new Quiz();
        Essay essayStatus = new Essay();
        while (true) {
            if(!examStatus.isFinished() && !quizStatus.isFinished() && !essayStatus.isFinished()){
                System.out.println("Please select of the following: 'EXAM' 'QUIZ' 'ESSAY'");
                String choice = userInput.nextLine();
                getGradeType(userInput, choice, examStatus, quizStatus, essayStatus);
            }
            break;
        }
        addTotal("FINISH", 0);
    }

    private void getGradeType(Scanner userInput, String choice, Exam examStatus, Quiz quizStatus ,Essay essayStatus) {
        switch (choice) {
            case "EXAM":
                getExamInfo(userInput, examStatus);
                break;
            case "QUIZ":
                getQuizInfo(userInput, quizStatus);
                break;
            case "ESSAY":
                getEssayInfo(userInput, essayStatus);
                break;
            default:
                printErrorInvalidUserType();
        }
    }

    /*
     * EXAM
     */

    private void getExamInfo(Scanner userInput, Exam examStatus) {
        if (!examStatus.isFinished()) {
            while (true) {
                System.out.println("Please enter the course, year level, and score of the exam.");
                try {
                    String course = userInput.nextLine();
                    int yearLevel = Integer.parseInt(userInput.nextLine());
                    double score = Double.parseDouble(userInput.nextLine());
                    Exam examObject = new Exam(score, true);
                    examObject.setCourse(course);
                    examObject.setYearLevel(yearLevel);
                    displayExamInfo(examObject, examStatus);
                    addTotal("EXAM",score);
                } catch (NumberFormatException e) {
                    printErrorInvalidConversion();
                }
            }
        }
        printErrorAlreadyFinished();
        getType();
    }

    private void displayExamInfo(Exam examObject, Exam examStatus) {
        System.out.println("The exam grade for the course '"+examObject.getCourse()+"' for the year level of '"+examObject.getYearLevel()+"' is "+examObject.getScore());
        examStatus.setFinished(true);
    }

    /*
     * ESSAY
     */

    private void getEssayInfo(Scanner userInput, Essay essayStatus) {
        if (!essayStatus.isFinished()) {
            while (true) {
                System.out.println("Please enter the course, year level, and score of the essay.");
                try {
                    String course = userInput.nextLine();
                    int yearLevel = Integer.parseInt(userInput.nextLine());
                    double score = Double.parseDouble(userInput.nextLine());
                    Essay essayObject = new Essay(score, true);
                    essayObject.setCourse(course);
                    essayObject.setYearLevel(yearLevel);
                    displayEssayInfo(essayObject, essayStatus);
                    addTotal("ESSAY" , score);
                } catch (NumberFormatException e) {
                    printErrorInvalidConversion();
                }
            }
        }
        printErrorAlreadyFinished();
        getType();
    }

    private void displayEssayInfo(Essay essayObject, Essay essayStatus) {
        System.out.println("The essay grade for the course '"+essayObject.getCourse()+"' for the year level of '"+essayObject.getYearLevel()+"' is "+ essayObject.getScore());
        essayObject.setFinished(true);
    }

    /*
     * QUIZ
     */

    private void getQuizInfo(Scanner userInput, Quiz quizStatus) {
        if (!quizStatus.isFinished()) {
            while (true) {
                System.out.println("Please enter the course, year level, and score of the essay.");
                try {
                    String course = userInput.nextLine();
                    int yearLevel = Integer.parseInt(userInput.nextLine());
                    double score = Double.parseDouble(userInput.nextLine());
                    Quiz quizObject = new Quiz(score, true);
                    quizObject.setCourse(course);
                    quizObject.setYearLevel(yearLevel);
                    displayQuizInfo(quizObject, quizStatus);
                    addTotal("QUIZ",score);
                } catch (NumberFormatException e) {
                    printErrorInvalidConversion();
                }
            }
        }
        printErrorAlreadyFinished();
        getType();
    }

    private void displayQuizInfo(Quiz quizObject, Quiz quizStatus) {
        System.out.println("The quiz grade for the course '"+quizObject.getCourse()+"' for the year level of '"+quizObject.getYearLevel()+"' is "+ quizObject.getScore());
        quizStatus.setFinished(true);
    }

    private void addTotal(String type, double score) {
        if (type == "EXAM") {
            double examScore = score * 0.50;
        }
        if (type == "QUIZ") {
            double quizScore = score * 0.20;
        }
        if (type == "ESSAY") {
            double essayScore = score * 0.30;
        }
        if (type == "FINISH") {

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
            pause(300);
        }
        clearScreen();
    }
    
    private void printErrorInvalidUserType() {
        clearScreen();
        System.out.print("Invalid type, please try again!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            pause(300);
        }
        clearScreen();
    }

    private void printErrorAlreadyFinished() {
        clearScreen();
        System.out.print("That type is already finished! Try another one!");
        char[] characters = {'.', ' ', '.', ' ', '.', ' ', '.'};

        for(int i = 0; i < characters.length; i++) {
            System.out.print(characters[i]);
            pause(300);
        }
        clearScreen();
    }

    public void run() {}
    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
