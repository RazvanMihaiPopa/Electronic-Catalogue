import java.util.ArrayList;

public class BestExamScore implements BestScoreStrategy {
    @Override
    public Student getBestScore(ArrayList<Grade> grades) {
        Grade bestGrade = grades.get(0);
        for(Grade g: grades) {
            if(g.getExamScore() - bestGrade.getExamScore() > 0) {
                bestGrade = g;
            }
        }
        return bestGrade.getStudent();
    }
}
