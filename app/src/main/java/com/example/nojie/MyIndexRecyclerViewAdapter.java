package com.example.nojie;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nojie.fragment.Home.Inspiration;

import java.util.List;

public class MyIndexRecyclerViewAdapter extends RecyclerView.Adapter<MyIndexRecyclerViewAdapter.ViewHolder>{

    private final List<Inspiration> mValues;
    private onRecyclerItemClickLitener onRecyclerItemClickLitener;

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mBlockTitle;
        final TextView mContentTitle;
        final TextView mBlockContent;
        final TextView mBlockDate;


        public Inspiration mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mBlockTitle = view.findViewById(R.id.block_title);
            mContentTitle = view.findViewById(R.id.article_title);
            mBlockContent =  view.findViewById(R.id.block_content);
            mBlockDate = view.findViewById(R.id.block_date);
        }
    }

    public MyIndexRecyclerViewAdapter(List<Inspiration> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homepage_recyclerviewitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
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
        holder.mItem = mValues.get(position);
        holder.mBlockDate.setText(holder.mItem.getBlock_date());
        holder.mBlockContent.setText(holder.mItem.getBlock_content());
        holder.mContentTitle.setText(holder.mItem.getArticle_title());
        holder.mBlockTitle.setText(holder.mItem.getBlock_title());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    /**
     * 指定位置移除item
     */
    public void removeData(int position) {
        mValues.remove(position);
        notifyItemRemoved(position);
    }
    public interface onRecyclerItemClickLitener {
        void onRecyclerItemClick(RecyclerView.ViewHolder view, int position);
        void onRecyclerItemLongClick(RecyclerView.ViewHolder view , int position);
    }
    public void setOnRecyclerItemClickLitener(onRecyclerItemClickLitener onRecyclerItemClickLitener)
    {
        this.onRecyclerItemClickLitener = onRecyclerItemClickLitener;
    }
    /**
     * 为RecyclerView添加上拉加载更多的实现接口
    * firstVisibleItem=页面显示的第一个Item的Position
    * visibleItemCount=页面显示的Item的数量
    * totalItemCount=总共的Item的数量
    * previousTotal=与totalItemCount做比较，用于判断是否可以执行加载
    * loading=是否处于加载中
    * currentPage=页数
    * firstVisibleItem+visibleItemCount=totalItemCount 即拉倒了最底部。
    * 当页面刷新时，必须将previousTotal变为0.否则无法执行上拉加载
    */
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
        public void clearPreviousTotal(){
            previousTotal=0;
        }
    }
}
