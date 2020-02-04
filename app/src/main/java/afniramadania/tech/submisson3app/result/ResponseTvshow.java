package afniramadania.tech.submisson3app.result;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTvshow {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public ResponseTvshow(ANError anError){

    }

    private ANError anError;

    public ANError getAnError() {return anError;}

    public void setPage(int page){
        this.page = page;
    }

    public int getPage(){
        return page;
    }

    public void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }

    public int getTotalPages(){
        return totalPages;
    }

    public void setResults(List<ResultsItem> results){
        this.results = results;
    }

    public List<ResultsItem> getResults(){
        return results;
    }

    public void setTotalResults(int totalResults){
        this.totalResults = totalResults;
    }

    public int getTotalResults(){
        return totalResults;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results = '" + totalResults + '\'' +
                        "}";
    }

}
