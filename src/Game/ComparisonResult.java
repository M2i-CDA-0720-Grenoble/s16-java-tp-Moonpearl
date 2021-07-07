package Game;

public class ComparisonResult
{
    
    private int correct;
    private int misplaced;
    private int absent;

    public ComparisonResult(int correct, int misplaced, int absent)
    {
        this.correct = correct;
        this.misplaced = misplaced;
        this.absent = absent;
    }

    public int getTotal()
    {
        return correct + misplaced + absent;
    }

    public String display()
    {
        String result = "";
        for (int i = 0; i < correct; i += 1) {
            result += "O ";
        }
        for (int i = 0; i < misplaced; i += 1) {
            result += "X ";
        }
        for (int i = 0; i < absent; i += 1) {
            result += "- ";
        }
        return result;
    }

    public boolean isCorrect()
    {
        return misplaced == 0 && absent == 0;
    }

}
