package College;

import java.util.Scanner;

public class Grades implements Runnable {
    public void getType() {
        Scanner userInput = new Scanner(System.in);

        // add if statement to check if all processes are done.
        Exam examStatus = new Exam();
        Quiz quizStatus = new Quiz();
        Essay essayStatus = new Essay();
        if(!examStatus.isFinished() && !quizStatus.isFinished() && !quizStatus.isFinished()){

        }
        System.out.println("Enter a selected");

        
        System.out.println("Please enter the course, year level, and score of the exam.");
        getExamInfo(userInput);
        System.out.println("Please enter the course, year level, and score of the essay.");
        getEssayInfo(userInput);
        System.out.println("Please enter the course, year level, and score of the exams.");
        getQuizInfo(userInput);
    }
    /*
     * EXAM
     */

    private void getExamInfo(Scanner userInput) {
        Exam exam = new Exam();
        if (!exam.isFinished()) {
            while (true) {
                try {
                    Midterm midterm = new Midterm();
                    midterm.printGoodLuck();
                    String course = userInput.nextLine();
                    int yearLevel = Integer.parseInt(userInput.nextLine());
                    double score = Double.parseDouble(userInput.nextLine());
                    Exam examObject = new Exam(score, true);
                    examObject.setCourse(course);
                    examObject.setYearLevel(yearLevel);
                    displayExamInfo(examObject);
                } catch (Exception e) {
                    
                }
            }
        }
        printErrorAlreadyFinished();
        getType();
    }

    private void displayExamInfo(Exam examObject) {
        System.out.println("The exam grades for the course '"+examObject.getCourse()+"' for the year level of '"+examObject.getYearLevel()+"' is "+examObject.getScore());
        examObject.setFinished(true);
    }

    /*
     * ESSAY
     */

    private void getEssayInfo(Scanner userInput) {

    }

    private void displayEssayInfo() {

    }

    /*
     * QUIZ
     */

    private void getQuizInfo(Scanner userInput) {

    }

    private void displayQuizInfo() {

    }
    
    /*
      * EXTERNAL
      */
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
