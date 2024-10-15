package College;

public class Midterm extends Grades {
    private String message;
    private String course;
    private int yearLevel;

    public void printGoodLuck() {
        System.out.println(message);
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public int getYearLevel() {
        return yearLevel;
    }
}


