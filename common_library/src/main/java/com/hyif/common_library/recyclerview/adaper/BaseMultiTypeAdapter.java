package com.hyif.common_library.recyclerview.adaper;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.hyif.common_library.recyclerview.adapterdelegate.AdapterDelegate;
import com.hyif.common_library.recyclerview.adapterdelegate.AdapterDelegatesManager;
import com.hyif.common_library.recyclerview.model.DisplayableItem;

import java.util.List;

/**
 * 描述: 多布局适配器
 * 作者: hyif
 * 创建日期：2017/8/7 on 21:45
 */

public class BaseMultiTypeAdapter extends RecyclerView.Adapter {
    private AdapterDelegatesManager<List<DisplayableItem>> delegatesManager;
    private List<DisplayableItem> items;
    public BaseMultiTypeAdapter(Activity activity, List<DisplayableItem> items) {
        this.items = items;
        delegatesManager = new AdapterDelegatesManager<>();
    }

    public void addDelegate(AdapterDelegate delegate) {
        delegatesManager.addDelegate(delegate);
    }
    @Override public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        delegatesManager.onBindViewHolder(items, position, holder, payloads);
    }

    @Override public int getItemCount() {
        return items.size();
    }
}
