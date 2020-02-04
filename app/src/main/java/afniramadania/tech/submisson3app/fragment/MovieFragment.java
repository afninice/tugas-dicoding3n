package afniramadania.tech.submisson3app.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import afniramadania.tech.submisson3app.R;
import afniramadania.tech.submisson3app.adapter.MoviesAdapter;
import afniramadania.tech.submisson3app.detail.DetailMovieActivity;
import afniramadania.tech.submisson3app.result.ResultsItem;
import afniramadania.tech.submisson3app.result.ResponseMovies;
import afniramadania.tech.submisson3app.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    private Observer<ResponseMovies> getMovies = new Observer<ResponseMovies>() {
        @Override
        public void onChanged(ResponseMovies responseMovies) {
            if (responseMovies != null) {
                if (responseMovies.getAnError() == null) {
                    MoviesAdapter mAdapter = new MoviesAdapter(responseMovies.getResults());
                    mAdapter.SetOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent goToDetailMovie = new Intent(view.getContext(), DetailMovieActivity.class);
                            goToDetailMovie.putExtra(DetailMovieActivity.SELECTED_MOVIE, model);
                            startActivity(goToDetailMovie);

                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    alertDialog.setMessage(responseMovies.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        return inflater.inflate(R.layout.fragment_movie, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.proBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager= new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        MovieViewModel mViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mViewModel.doRequestListMovies();
        mViewModel.getMovies().observe(this,getMovies);
    }

}
