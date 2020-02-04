package afniramadania.tech.submisson3app.detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bumptech.glide.Glide;

import java.util.Objects;

import afniramadania.tech.submisson3app.R;
import afniramadania.tech.submisson3app.databinding.ActivityTvshowsBinding;
import afniramadania.tech.submisson3app.result.ResultsItemTv;

public class DetailTvshowActivity extends AppCompatActivity {

    public static final String SELECTED_TV_SHOWS = "selected_tv_shows";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetailTvShowsModel viewModel = ViewModelProviders.of(this).get(DetailTvShowsModel.class);
        ActivityTvshowsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tvshow);
        ResultsItemTv tvModel = getIntent().getParcelableExtra(SELECTED_TV_SHOWS);
        viewModel.setResultsItemTv(tvModel);
        binding.setViewmodel(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+tvModel.getPosterPath()).into(binding.imgPoster);
        setTitle(viewModel.getResultsItemTv().getName());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
