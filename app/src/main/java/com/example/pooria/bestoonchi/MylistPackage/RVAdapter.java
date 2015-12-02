package com.example.pooria.bestoonchi.MylistPackage;

/**
 * Created by mm on 12/2/2015.
 */

        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;


        import com.example.pooria.bestoonchi.MainActivity;
        import com.example.pooria.bestoonchi.R;
        import com.example.pooria.bestoonchi.model.Darkhast;

        import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {


    public static class PersonViewHolder extends RecyclerView.ViewHolder {


        CardView cv;
        TextView title;
        TextView gheymat;
        ImageView photoId;


        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.darkhast_title);
            gheymat = (TextView)itemView.findViewById(R.id.darkhast_gheymat);
            photoId = (ImageView)itemView.findViewById(R.id.darkhast_photo);

            //onClick Event for each items
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "From RVAdapter- Title :  " + title.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    List<Darkhast> darkhasts;


    public RVAdapter(List<Darkhast> darkhasts){
        this.darkhasts = darkhasts;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //connect layout to this viewGroup
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.title.setText(darkhasts.get(i).title);
        personViewHolder.gheymat.setText(darkhasts.get(i).gheymat);
        personViewHolder.photoId.setImageResource(darkhasts.get(i).photoId);
    }


    @Override
    public int getItemCount() {
        return darkhasts.size();
    }
}
