package com.davtyan.materialweather.lib.testable;

import android.support.v7.widget.RecyclerView;

public abstract class TestableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public void notifyDataSetChangedNonFinal() {
        super.notifyDataSetChanged();
    }
}
