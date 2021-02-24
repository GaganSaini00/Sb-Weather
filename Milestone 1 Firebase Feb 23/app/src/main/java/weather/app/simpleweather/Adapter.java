package weather.app.simpleweather;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class Adapter extends FirebaseRecyclerAdapter<Model,Adapter.mainViewHolder> {
    private FirebaseRecyclerOptions<Model> setdata;
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<Model> options,HomeActivity activity) {
        super(options);
        this.setdata = options;
        this.context = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull mainViewHolder holder, int position, @NonNull Model model) {

        TextView tempname = holder.namemeasure;
        TextView tempval = holder.val;

        tempname.setText("Humidity");
        tempval.setText(model.getHumidity().toString());

    }

    @NonNull
    @Override
    public mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page,parent,false);
        view.setOnClickListener(HomeActivity.myOnClickListener);
        mainViewHolder mainholder = new mainViewHolder(view);
        return mainholder;
    }

    public static class mainViewHolder extends RecyclerView.ViewHolder
    {           TextView namemeasure;
                TextView val;



        public mainViewHolder(@NonNull View itemView) {
            super(itemView);

            this.namemeasure = (TextView) itemView.findViewById(R.id.weathername);
            this.val = (TextView) itemView.findViewById(R.id.weathervalue);


        }
    }
}
