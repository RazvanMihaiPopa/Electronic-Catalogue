import java.util.ArrayList;

public class BestPartialScore implements BestScoreStrategy{
    @Override
    public Student getBestScore(ArrayList<Grade> grades) {
        Grade bestGrade = grades.get(0);
        for(Grade g: grades) {
            if(g.getPartialScore() - bestGrade.getPartialScore() > 0) {
                bestGrade = g;
            }
        }
        return bestGrade.getStudent();
    }
}
