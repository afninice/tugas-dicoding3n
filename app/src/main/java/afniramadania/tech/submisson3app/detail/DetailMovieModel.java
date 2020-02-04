package afniramadania.tech.submisson3app.detail;

import androidx.lifecycle.ViewModel;

import afniramadania.tech.submisson3app.result.ResultsItem;

public class DetailMovieModel extends ViewModel {

    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }


}
