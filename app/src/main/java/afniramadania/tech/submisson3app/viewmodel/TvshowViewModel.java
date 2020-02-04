package afniramadania.tech.submisson3app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import afniramadania.tech.submisson3app.MovieMain;
import afniramadania.tech.submisson3app.result.ResponseTvshow;

public class TvshowViewModel extends ViewModel {

    private MutableLiveData<ResponseTvshow> responseTvShows = new MutableLiveData<>();

   public MutableLiveData getTvShowList() {
        if (responseTvShows == null) {
            doRequestListTvShows();
        }
        return responseTvShows;
    }

 public void doRequestListTvShows() {
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/tv?api_key=160405bacfb30272452bbad6b2e56c3a")
                .addQueryParameter("api_key", MovieMain.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseTvshow.class, new ParsedRequestListener<ResponseTvshow>() {
                    @Override
                    public void onResponse(ResponseTvshow responsetv) {
                        responseTvShows.postValue(responsetv);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseTvShows.setValue(new ResponseTvshow(anError));
                    }
                });
    }

}
