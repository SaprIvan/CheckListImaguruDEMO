package com.example.checklistimaguru;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.checklistimaguru.R.drawable.design_snackbar_background;
import static com.example.checklistimaguru.R.drawable.logo_room_circle;


class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private CheckListsArray elements;
    DataAdapter(Context context, CheckListsArray elements){
        this.elements = elements;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.element_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

            final CheckList checkList = elements.get(i);
            viewHolder.roomName.setText(checkList.getRoom());
            viewHolder.logo.setText(checkList.getLogo());
            viewHolder.logo.getBackground().setColorFilter(Color.parseColor(checkList.getColor()), PorterDuff.Mode.SRC_OVER);
            viewHolder.eventName.setText(checkList.getNAME());
            viewHolder.time.setText(checkList.getBeginTime() + " - " + checkList.getEndTime());
            viewHolder.date.setText(checkList.getDate());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context , elementActivity.class);
                    intent.putExtra(CheckList.class.getSimpleName(), checkList);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView logo;
        TextView roomName;
        TextView eventName;
        TextView time;
        TextView date;
        ConstraintLayout layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logoRoom);
            roomName = itemView.findViewById(R.id.roomName);
            eventName = itemView.findViewById(R.id.eventName);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            layout = itemView.findViewById(R.id.element_layout);
        }
    }
}

