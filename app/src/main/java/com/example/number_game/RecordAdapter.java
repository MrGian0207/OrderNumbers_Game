package com.example.number_game;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter< RecordAdapter.ViewHolder> {

    private List<TimeRecords> TimeRecords_List;

    public RecordAdapter(List<TimeRecords> TimeRecords_List) {
        this.TimeRecords_List = TimeRecords_List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_records, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeRecords time_record = TimeRecords_List.get(position);
        holder.textMinutes.setText(String.format("%02d :",time_record.getMinutes()));
        holder.textSeconds.setText(String.format(" %02d",time_record.getSeconds()));
    }

    @Override
    public int getItemCount() {
        return TimeRecords_List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textMinutes, textSeconds;

        public ViewHolder(View itemView) {
            super(itemView);
            textMinutes = itemView.findViewById(R.id.textMinutes);
            textSeconds = itemView.findViewById(R.id.textSeconds);
        }
    }
}

