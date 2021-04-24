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

public class Humid_Adapter extends FirebaseRecyclerAdapter<Humid_Model,Humid_Adapter.MainViewHolder> {

    private FirebaseRecyclerOptions<Humid_Model> humid_modelFirebaseOptions;
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Humid_Adapter(@NonNull FirebaseRecyclerOptions<Humid_Model> options, Humid_Activity humidActivity) {
        super(options);
        this.humid_modelFirebaseOptions = options;
        this.context = humidActivity;
    }

    @Override
    protected void onBindViewHolder(@NonNull MainViewHolder holder, int position, @NonNull Humid_Model model) {

        TextView nametemp = holder.namemeasure;
        nametemp.setText("Humidity");
        TextView temperature = holder.val;
        temperature.setText(model.getHumidity().toString()+" %");


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
