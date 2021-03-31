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

public class Pressure_Adapter extends FirebaseRecyclerAdapter<Pressure_Model,Pressure_Adapter.MainViewHolder> {
    private FirebaseRecyclerOptions<Pressure_Model> pressureActivityFirebaseOptions;
    Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Pressure_Adapter(@NonNull FirebaseRecyclerOptions<Pressure_Model> options, Pressure_Activity pressureActivity) {
        super(options);
        this.pressureActivityFirebaseOptions = options;
        this.context = pressureActivity;
    }

    @Override
    protected void onBindViewHolder(@NonNull MainViewHolder holder, int position, @NonNull Pressure_Model model) {


        TextView nametemp = holder.namemeasure;
        nametemp.setText("Air Pressure");
        TextView temperature = holder.val;
        temperature.setText(model.getAirpressure().toString()+" kpa");


    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page,parent,false);
        view.setOnClickListener(HomeActivity.myOnClickListener);
       MainViewHolder mainholder = new MainViewHolder(view);
        return mainholder;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        TextView namemeasure, val;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            this.namemeasure = (TextView) itemView.findViewById(R.id.weathername);
            this.val = (TextView) itemView.findViewById(R.id.weathervalue);
        }
    }
}
