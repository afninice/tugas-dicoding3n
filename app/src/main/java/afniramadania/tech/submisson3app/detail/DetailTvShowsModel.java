package afniramadania.tech.submisson3app.detail;

import androidx.lifecycle.ViewModel;

import afniramadania.tech.submisson3app.result.ResultsItemTv;

public class DetailTvShowsModel extends ViewModel {

    private ResultsItemTv resultsItemtv;

    public ResultsItemTv getResultsItemTv() {
        return resultsItemtv;
    }

    public void setResultsItemTv(ResultsItemTv resultsItemtv) {
        this.resultsItemtv = resultsItemtv;

    }

}
