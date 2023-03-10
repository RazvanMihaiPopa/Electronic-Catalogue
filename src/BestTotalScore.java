import java.util.ArrayList;

public class BestTotalScore implements BestScoreStrategy{
    @Override
    public Student getBestScore(ArrayList<Grade> grades) {
        Grade bestGrade = grades.get(0);
        for(Grade g: grades) {
            if(g.compareTo(bestGrade) == -1) {
                bestGrade = g;
            }
        }
        return bestGrade.getStudent();
    }
}
