package kiddomchallenge.vikramtandonapps.com.model;

import java.io.Serializable;

public class ItemListElementModel implements Serializable {

    Result result;
    double resultScore;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public double getResultScore() {
        return resultScore;
    }

    public void setResultScore(double resultScore) {
        this.resultScore = resultScore;
    }
}
