package College;

public class Quiz extends Midterm {
    private double score;
    private boolean status;

    public Quiz(double score, boolean status) {
        this.score = score;
        this.status = status;
    }

    public Quiz() {
        this.status = false;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setFinished(boolean status) {
        this.status = status;
    }

    public boolean isFinished() {
        return status;
    }
}
