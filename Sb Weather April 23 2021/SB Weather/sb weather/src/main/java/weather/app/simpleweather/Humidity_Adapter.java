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

public class Humidity_Adapter extends FirebaseRecyclerAdapter<Humidity_Model, Humidity_Adapter.mainViewHolder> {
    private FirebaseRecyclerOptions<Humidity_Model> setdata;
    Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Humidity_Adapter(@NonNull FirebaseRecyclerOptions<Humidity_Model> options, Activity activity) {
        super(options);
        this.setdata = options;
        this.context = activity;

    }

    @Override
    protected void onBindViewHolder(@NonNull Humidity_Adapter.mainViewHolder holder, int position, @NonNull Humidity_Model model) {
        TextView namehumid = holder.humidname;
        namehumid.setText("Humidity");
        TextView valhumid = holder.humidval;
        valhumid.setText(model.getHumidity().toString()+"%");
    }

    @NonNull
    @Override
    public Humidity_Adapter.mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.humidity_page,parent,false);
        view.setOnClickListener(Temperature_Activity.myOnClickListener);
        mainViewHolder mainholder = new mainViewHolder(view);
        return mainholder;


    }

    public class mainViewHolder extends RecyclerView.ViewHolder {
        TextView humidname, humidval;

        public mainViewHolder(@NonNull View itemView) {
            super(itemView);
            this.humidname = (TextView) itemView.findViewById(R.id.humidname);
            this.humidval  = (TextView) itemView.findViewById(R.id.humidvalue);
        }
    }
}
