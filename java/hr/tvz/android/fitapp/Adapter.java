package hr.tvz.android.fitapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<Exercise> exerciseList;
    private Context mContext;


    public Adapter(ArrayList<Exercise> exerciseList, Context mContext) {
        this.exerciseList = exerciseList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        //adding Glide
        Glide.with(mContext)
                .asBitmap()
                .load(exerciseList.get(position).getImageURL())
                .into(holder.imageURL);
        holder.textItem.setText(exerciseList.get(position).getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, exerciseList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SingleExcerciseActivity.class);
                intent.putExtra("name", exerciseList.get(position).getName());
                intent.putExtra("img_url", exerciseList.get(position).getImageURL());
                mContext.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {

        return exerciseList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageURL;
        TextView textItem;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            imageURL = itemView.findViewById(R.id.imageWorkout_id);
            cardView = itemView.findViewById(R.id.cardview_id);
            textItem = itemView.findViewById(R.id.textViewItem_id);
        }
    }
}
