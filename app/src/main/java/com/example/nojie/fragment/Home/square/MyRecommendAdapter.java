package com.example.nojie.fragment.Home.square;

import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nojie.MyIndexRecyclerViewAdapter;
import com.example.nojie.R;

import java.util.List;

public class MyRecommendAdapter extends RecyclerView.Adapter<MyRecommendAdapter.ViewHolder> {

    private List<Recommend_Model> MyData;
    private MyIndexRecyclerViewAdapter.onRecyclerItemClickLitener onRecyclerItemClickLitener;

    class ViewHolder extends RecyclerView.ViewHolder {

        Recommend_Model mItem;
        final View mView;
        final TextView mTitle;
        final TextView mOne_short_word;
        final TextView mLocation;
        final ImageView mImageView;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mTitle=mView.findViewById(R.id.square_recommend_title);
            mImageView=mView.findViewById(R.id.square_recommend_image);
            mLocation=mView.findViewById(R.id.square_recommend_location);
            mOne_short_word=mView.findViewById(R.id.square_recommend_one_word);

        }

    }
    public MyRecommendAdapter(List<Recommend_Model> item){
        MyData=item;
    }
    @NonNull
    @Override
    public MyRecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.square_recommend_recycleritem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecommendAdapter.ViewHolder holder, final int position) {
        if(onRecyclerItemClickLitener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClickLitener.onRecyclerItemClick(holder,position);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onRecyclerItemClickLitener.onRecyclerItemLongClick(holder,position);
                    return false;
                }
            });
        }
        holder.mItem=MyData.get(position);
        holder.mLocation.setText(holder.mItem.getLocation());
        holder.mOne_short_word.setText(holder.mItem.getOn_short_word());
        holder.mTitle.setText(holder.mItem.getTitle());
    }

    /**
     * 指定位置移除item
     */
    public void removeData(int position) {
        MyData.remove(position);
        notifyItemRemoved(position);
    }

    public interface onRecyclerItemClickLitener {
        void onRecyclerItemClick(RecyclerView.ViewHolder view, int position);
        void onRecyclerItemLongClick(RecyclerView.ViewHolder view , int position);
    }
    public void setOnRecyclerItemClickLitener(MyIndexRecyclerViewAdapter.onRecyclerItemClickLitener onRecyclerItemClickLitener)
    {
        this.onRecyclerItemClickLitener = onRecyclerItemClickLitener;
    }
    @Override
    public int getItemCount() {

        return MyData.size();
    }

    public abstract static class LoadMoreRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
        private int previousTotal = 0;
        private boolean loading = true;
        int firstVisibleItem, visibleItemCount, totalItemCount;
        private int currentPage = 1;

        private LinearLayoutManager mLinearLayoutManager;

        public LoadMoreRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
                currentPage++;
                onLoadMore(currentPage);
                loading = true;
            }
        }

        public abstract void onLoadMore(int currentPage);
    }
}
