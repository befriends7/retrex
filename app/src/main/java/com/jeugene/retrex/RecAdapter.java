package com.jeugene.retrex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.Classholder>
{

    protected ArrayList<fin> datalist;
    private onItemClickListener onItemClickListener;


    public RecAdapter(ArrayList<fin> datalist) {
        this.datalist = datalist;
    }

    public onItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public Classholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new Classholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Classholder holder, int position) {

            fin data = datalist.get(position);

                Picasso.get().load(R.drawable.news).fit().into(holder.image1);
                holder.title.setText(data.getHead());



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(data);
            }
        };
        holder.image1.setOnClickListener(listener);
        holder.title.setOnClickListener(listener);


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class Classholder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.image1)
        ImageView image1;

        @BindView(R.id.title)
        TextView title;

        public Classholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
