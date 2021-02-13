package talaso.learning.quizapp;

public class TrueOrFalse {
    private int mQuestionID;
    private boolean mAnswer;
    public TrueOrFalse(int questionResourceID,boolean TrueOrFalse){
        mQuestionID = questionResourceID;
        mAnswer = TrueOrFalse;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
