package MidtermGradingSystem;

public class Midterm extends Grades {
    private String title;
    private double  totalScore;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }
}


