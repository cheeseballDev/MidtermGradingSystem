package MidtermGradingSystem;

import java.util.Scanner;

public class Grades implements Runnable {
    private double examScore = 0;
    private double quizScore = 0;
    private double essayScore = 0;

    public void getType() {
        clearScreen();
        Scanner userInput = new Scanner(System.in);
        Exam examStatus = new Exam();
        Quiz quizStatus = new Quiz();
        Essay essayStatus = new Essay();
        while (true) {
            if(examStatus.isFinished() && quizStatus.isFinished() && essayStatus.isFinished()){
                break;
            }
            System.out.println("Please select of the following: 'EXAM' 'QUIZ' 'ESSAY'");
            String choice = userInput.nextLine().toUpperCase().trim();
            getGradeType(userInput, choice, examStatus, quizStatus, essayStatus);
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
                getType();
        }
    }

    /*
     * EXAM
     */

    private void getExamInfo(Scanner userInput, Exam examStatus) {
        if (examStatus.isFinished()) {
            printErrorAlreadyFinished();
        } else {
            while (true) {
            System.out.println("Please enter the course, year level, and score of the exam.");
                try {
                    String course = userInput.nextLine();
                    int yearLevel = Integer.parseInt(userInput.nextLine());
                    double score = Double.parseDouble(userInput.nextLine());
                    Exam examObject = new Exam(score, true);
                    examObject.setCourse(course);
                    examObject.setYearLevel(yearLevel);
                    displayExamInfo(examObject);
                    addTotal("EXAM",score);
                    break;
                } catch (NumberFormatException e) {
                    printErrorInvalidConversion();
                }
            }
        examStatus.setFinished(true);
        }
    }

    private void displayExamInfo(Exam examObject) {
        System.out.println("The exam grade for the course '"+examObject.getCourse()+"' for the year level of '"+examObject.getYearLevel()+"' is "+examObject.getScore());
    }

    /*
     * ESSAY
     */

    private void getEssayInfo(Scanner userInput, Essay essayStatus) {
        if (essayStatus.isFinished()) {
            printErrorAlreadyFinished();
        } else {
            while (true) {
                    System.out.println("Please enter the course, year level, and score of the essay.");
                try {
                    String course = userInput.nextLine();
                    int yearLevel = Integer.parseInt(userInput.nextLine());
                    double score = Double.parseDouble(userInput.nextLine());
                    Essay essayObject = new Essay(score, true);
                    essayObject.setCourse(course);
                    essayObject.setYearLevel(yearLevel);
                    displayEssayInfo(essayObject);
                    addTotal("ESSAY", score);
                    break;
                } catch (NumberFormatException e) {
                    printErrorInvalidConversion();
                }
            }
            essayStatus.setFinished(true);
        }
    }

    private void displayEssayInfo(Essay essayObject) {
        System.out.println("The essay grade for the course '"+essayObject.getCourse()+"' for the year level of '"+essayObject.getYearLevel()+"' is "+ essayObject.getScore());
    }

    /*
     * QUIZ
     */

    private void getQuizInfo(Scanner userInput, Quiz quizStatus) {
        if (quizStatus.isFinished()) {
            printErrorAlreadyFinished();
        } else {
            while (true) {
                    System.out.println("Please enter the title, the score you got, and the total score of the quiz.");
                try {
                    String title = userInput.nextLine();
                    double score = Double.parseDouble(userInput.nextLine());
                    double totalScore= Double.parseDouble(userInput.nextLine());
                    Quiz quizObject = new Quiz(score, true);
                    quizObject.setTitle(title);
                    quizObject.setTotalScore(totalScore);
                    displayQuizInfo(quizObject);
                    addTotal("QUIZ", score);
                    break;
                } catch (NumberFormatException e) {
                    printErrorInvalidConversion();
                }
            }
            quizStatus.setFinished(true);
        }
    }

    private void displayQuizInfo(Quiz quizObject) {
        System.out.println("Your quiz, titled '" + quizObject.getTitle() +"' is scored at " + quizObject.getScore() + " out of " + quizObject.getTotalScore());
    }

    private void addTotal(String type, double score) {
        if (type.equals("EXAM")) {
            examScore = score * 0.50;
        }
        if (type.equals("QUIZ")) {
            quizScore = score * 0.20;
        }
        if (type.equals("ESSAY")) {
            essayScore = score * 0.30;
        }
        if (type.equals("FINISH")) {
            double totalScore = examScore + quizScore + essayScore;
            System.out.println("The total grade of your midterms is " + totalScore);
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

    private void printErrorAlreadyFinished() {
        clearScreen();
        System.out.print("That type is already finished! Try another one!");
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
