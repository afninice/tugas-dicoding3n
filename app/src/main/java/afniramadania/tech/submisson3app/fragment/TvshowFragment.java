package afniramadania.tech.submisson3app.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import afniramadania.tech.submisson3app.adapter.TvshowAdapter;
import afniramadania.tech.submisson3app.detail.DetailTvshowActivity;
import afniramadania.tech.submisson3app.result.ResponseTvshow;
import afniramadania.tech.submisson3app.result.ResultsItemTv;
import afniramadania.tech.submisson3app.viewmodel.TvshowViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowFragment extends Fragment {


    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer<ResponseTvshow> getTvShows =new Observer<ResponseTvshow>() {
        @Override
        public void onChanged(ResponseTvshow responseTvShow) {
            if (responseTvShow!=null){
                if (responseTvShow.getAnError()==null){
                    TvshowAdapter mAdapter = new TvshowAdapter(responseTvShow.getResults());
                    mAdapter.SetOnItemClickListener(new TvshowAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItemTv model) {
                            Intent keDetailMovie = new Intent(view.getContext(), DetailTvshowActivity.class);
                            keDetailMovie.putExtra(DetailTvshowActivity.SELECTED_TV_SHOWS, model);
                            startActivity(keDetailMovie);
                        }
                    });

                    recyclerView.setAdapter(mAdapter);
                }else {
                    alertDialog.setMessage(responseTvShow.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.proBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        TvshowViewModel mViewModel = ViewModelProviders.of(this).get(TvshowViewModel.class);
        mViewModel.doRequestListTvShows();
        mViewModel.getTvShowList().observe(this, getTvShows);

    }

}
