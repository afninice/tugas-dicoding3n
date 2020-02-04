package afniramadania.tech.submisson3app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import afniramadania.tech.submisson3app.MovieMain;
import afniramadania.tech.submisson3app.result.ResponseMovies;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ResponseMovies> responseM = new MutableLiveData<>();

    public MutableLiveData getMovies(){
        if (responseM==null){
            doRequestListMovies();
        }
        return responseM;
    }

    public void doRequestListMovies() {
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", MovieMain.MOVIE_DB_API_KEY)
                .addQueryParameter("language","en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseMovies.class, new ParsedRequestListener<ResponseMovies>() {
                    @Override
                    public void onResponse(ResponseMovies response) {
                        responseM.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError){
                        responseM.setValue(new ResponseMovies(anError)) ;
                    }
                });
    }
}
