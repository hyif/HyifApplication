package com.hyif.common_library.recyclerview.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 描述:
 * 作者: hyif
 * 创建日期：2017/8/7 on 20:48
 */

public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int ITEM_TYPE_LOAD_MORE_VIEW = Integer.MAX_VALUE - 1;//加载更多
    public static final int ITEM_TYPE_NO_MORE_VIEW = Integer.MAX_VALUE - 2;//暂无更多
    public static final int ITEM_TYPE_LOAD_ERROR_VIEW = Integer.MAX_VALUE - 3;//加载出错
    public static final int ITEM_TYPE_HIDE_VIEW = Integer.MAX_VALUE - 4;//无视图，隐藏
    private Context mContext;
    private RecyclerView.Adapter mInnerAdapter;
    private View mLoadMoreView;
    private View mLoadErrorView;
    private View mNoMoreView;
    private int mCurrentItemType = ITEM_TYPE_LOAD_MORE_VIEW;

    private boolean isLoadError = false;
    private boolean isHaveStatesView = true;//底部是否有状态视图

    public LoadMoreWrapper(Context context, RecyclerView.Adapter innerAdapter) {
        this.mContext = context;
        this.mInnerAdapter = innerAdapter;
    }

    public void showLoadMore() {
        mCurrentItemType = ITEM_TYPE_LOAD_MORE_VIEW;
        isLoadError = false;
        isHaveStatesView = true;
        notifyItemChanged(getItemCount());
    }

    public void showLoadError() {
        mCurrentItemType = ITEM_TYPE_LOAD_ERROR_VIEW;
        isLoadError = false;
        isHaveStatesView = true;
        notifyItemChanged(getItemCount());
    }

    public void showNoMore() {
        mCurrentItemType = ITEM_TYPE_NO_MORE_VIEW;
        isLoadError = false;
        isHaveStatesView = true;
        notifyItemChanged(getItemCount());
    }

    public void hideLoadMore() {
        mCurrentItemType = ITEM_TYPE_HIDE_VIEW;
        isHaveStatesView = false;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(isHaveStatesView&&isFooterItem(position)) {
            return mCurrentItemType;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE_LOAD_MORE_VIEW) {
            return getLoadMoreViewHolder();
        }else if(viewType==ITEM_TYPE_LOAD_ERROR_VIEW){
            return getLoadErrorViewHolder();
        }else if(viewType==ITEM_TYPE_NO_MORE_VIEW){
            return getNoMoreViewHolder();
        }
        return mInnerAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==ITEM_TYPE_LOAD_ERROR_VIEW) {//加载出错，设置回调函数
            mLoadErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(!isFooterType(holder.getItemViewType())) {
            mInnerAdapter.onBindViewHolder(holder,position);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (position == getItemCount() - 1 && isHaveStatesView) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null && isHaveStatesView) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (holder.getLayoutPosition() == getItemCount() - 1 && isHaveStatesView) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

                p.setFullSpan(true);
            }
        }
    }

    private ViewHolder getLoadMoreViewHolder() {
        if(mLoadMoreView==null) {
            mLoadMoreView = new TextView(mContext);
            mLoadMoreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mLoadMoreView.setPadding(20, 20, 20, 20);
            ((TextView) mLoadMoreView).setText("正在加载中");
            ((TextView) mLoadMoreView).setGravity(Gravity.CENTER);
        }
        return ViewHolder.createViewHolder(mContext,mLoadMoreView);
    }

    private ViewHolder getLoadErrorViewHolder () {
        if (mLoadErrorView == null) {
            mLoadErrorView = new TextView(mContext);
            mLoadErrorView.setPadding(20, 20, 20, 20);
            mLoadErrorView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams. WRAP_CONTENT));
            ((TextView) mLoadErrorView).setText("加载失败，请点我重试");
            ((TextView) mLoadErrorView).setGravity(Gravity.CENTER);
        }
        return ViewHolder.createViewHolder(mContext, mLoadErrorView);
    }
    private ViewHolder getNoMoreViewHolder() {
        if (mNoMoreView == null) {
            mNoMoreView = new TextView(mContext);
            mNoMoreView.setPadding(20, 20, 20, 20);
            mNoMoreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) mNoMoreView).setText("--end--");
            ((TextView) mNoMoreView).setGravity(Gravity.CENTER);
        }
        return ViewHolder.createViewHolder(mContext, mNoMoreView);
    }
    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount()+(isHaveStatesView?1:0);
    }

    private boolean isFooterType(int type) {
        return type == ITEM_TYPE_LOAD_MORE_VIEW ||
                type == ITEM_TYPE_LOAD_ERROR_VIEW ||
                type == ITEM_TYPE_NO_MORE_VIEW||
                type == ITEM_TYPE_HIDE_VIEW ;
    }

    private boolean isFooterItem(int position){
        return position==getItemCount()-1;
    }
}
