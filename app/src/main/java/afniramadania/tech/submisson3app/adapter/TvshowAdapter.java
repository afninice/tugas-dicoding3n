package afniramadania.tech.submisson3app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import afniramadania.tech.submisson3app.R;
import afniramadania.tech.submisson3app.result.ResultsItem;
import afniramadania.tech.submisson3app.result.ResultsItemTv;

public class TvshowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ResultsItemTv> ItemList;

    private OnItemClickListener mItemClickListener;

    public TvshowAdapter(List<ResultsItem> itemList){
        this.ItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ResultsItemTv model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTxtTitle.setText(model.getName());

            if (model.getOverview().length()>50) {
                genericViewHolder.itemTxtOverview.setText(model.getOverview().substring(0,49)+"...");
            }else {
                genericViewHolder.itemTxtOverview.setText(model.getOverview());
            }
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185"+model.getPosterPath()).into(genericViewHolder.imgPoster);
        }
    }

    @Override
    public int getItemCount()  {
        return ItemList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ResultsItemTv getItem(int position) {
        return ItemList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ResultsItemTv model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private TextView itemTxtTitle;
        private TextView itemTxtOverview;

        ViewHolder(final View itemView) {
            super(itemView);

            this.imgPoster = itemView.findViewById(R.id.img_Poster);
            this.itemTxtTitle = itemView.findViewById(R.id.item_txt_title);
            this.itemTxtOverview = itemView.findViewById(R.id.item_txt_overview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, ItemList.get(getAdapterPosition()));
                }
            });
        }
    }

}
