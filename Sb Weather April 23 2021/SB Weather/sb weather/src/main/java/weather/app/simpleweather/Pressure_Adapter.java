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

public class Pressure_Adapter extends FirebaseRecyclerAdapter<Pressure_Model,Pressure_Adapter.mainViewHolder> {
    private FirebaseRecyclerOptions<Pressure_Model> setdata;
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Pressure_Adapter(@NonNull FirebaseRecyclerOptions<Pressure_Model> options, Activity activity) {
        super(options);
        this.setdata = options;
        this.context = activity;

    }

    @Override
    protected void onBindViewHolder(@NonNull mainViewHolder holder, int position, @NonNull Pressure_Model model) {

        TextView namepressure = holder.pressurename;
        namepressure.setText("Air Pressure");
        TextView valpressure = holder.pressureval;
        valpressure.setText(model.getAirpressure().toString()+ "kpa");
    }

    @NonNull
    @Override
    public mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pressure_page,parent,false);
        view.setOnClickListener(Temperature_Activity.myOnClickListener);
        mainViewHolder mainholder = new mainViewHolder(view);
        return mainholder;


    }

    public class mainViewHolder extends RecyclerView.ViewHolder {
        TextView pressurename, pressureval;
        public mainViewHolder(@NonNull View itemView) {
            super(itemView);
            this.pressurename = (TextView) itemView.findViewById(R.id.pressurename);
            this.pressureval =  (TextView) itemView.findViewById(R.id.pressurevalue);
        }
    }
}
