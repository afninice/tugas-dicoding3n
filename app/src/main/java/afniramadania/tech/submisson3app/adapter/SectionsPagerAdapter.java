package afniramadania.tech.submisson3app.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import afniramadania.tech.submisson3app.R;
import afniramadania.tech.submisson3app.fragment.MovieFragment;
import afniramadania.tech.submisson3app.fragment.TvshowFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.tab_movies, R.string.tab_tv_shows};
    private final Context mContext;
    private MovieFragment moviesFragment;
    private TvshowFragment tvShowFragment;

    public  SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        moviesFragment = new MovieFragment();
        tvShowFragment = new TvshowFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return moviesFragment;
        } else {
            return tvShowFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }

}
