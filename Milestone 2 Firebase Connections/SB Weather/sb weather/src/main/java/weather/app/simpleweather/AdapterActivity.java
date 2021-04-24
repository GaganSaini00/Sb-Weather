package weather.app.simpleweather;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class AdapterActivity extends FirebaseRecyclerAdapter<ModelActivity,AdapterActivity.mainViewHolder> {

    private FirebaseRecyclerOptions<ModelActivity> setdata;
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterActivity(@NonNull FirebaseRecyclerOptions<ModelActivity> options,HomeActivity activity) {
        super(options);
        this.setdata = options;
        this.context = activity;
    }



    @Override
    protected void onBindViewHolder(@NonNull mainViewHolder holder, int position, @NonNull ModelActivity model) {




        TextView nametemp = holder.namemeasure;
        nametemp.setText("Temperature");
        TextView temperature = holder.val;
        temperature.setText(model.getTemperature().toString()+" °C");





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
    {           TextView namemeasure, val;




        public mainViewHolder(@NonNull View itemView) {
            super(itemView);

            this.namemeasure = (TextView) itemView.findViewById(R.id.weathername);
            this.val = (TextView) itemView.findViewById(R.id.weathervalue);


        }
    }


}
