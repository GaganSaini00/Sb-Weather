package weather.app.simpleweather;
//Sunshine Boys

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

public class Temperature_Adapter extends FirebaseRecyclerAdapter<Temperature_Model, Temperature_Adapter.mainViewHolder> {
private FirebaseRecyclerOptions<Temperature_Model> setdata;

Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Temperature_Adapter(@NonNull FirebaseRecyclerOptions<Temperature_Model> options, Activity activity) {
        super(options);
        this.setdata = options;
        this.context = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull Temperature_Adapter.mainViewHolder holder, int position, @NonNull Temperature_Model model) {
        TextView nametemp = holder.measurename;
        nametemp.setText("Temperature");
        TextView temperature = holder.val;
        temperature.setText(model.getTemperature().toString()+" °C");

    }

    @NonNull
    @Override
    public Temperature_Adapter.mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temperature_page,parent,false);
        view.setOnClickListener(Temperature_Activity.myOnClickListener);
        mainViewHolder mainholder = new mainViewHolder(view);
        return mainholder;


    }

    public class mainViewHolder extends RecyclerView.ViewHolder {
        TextView measurename, val;

        public mainViewHolder(@NonNull View itemView) {
            super(itemView);
            this.measurename = (TextView) itemView.findViewById(R.id.weathername);
            this.val = (TextView) itemView.findViewById(R.id.weathervalue);
        }
    }


/*public Temperature_AdapterActivity(ArrayList<Temperature_ModelActivity> weatherinfo, Activity activity)
{

    this.setdata = weatherinfo;
    this.context = activity;
}

    @NonNull
    @Override
    public mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temperature_page,parent,false);
        view.setOnClickListener(Temperature_Activity.myOnClickListener);
        mainViewHolder mainholder = new mainViewHolder(view);
        return mainholder;

    }

        @Override
        public void onBindViewHolder(@NonNull mainViewHolder holder, int position, @NonNull Temperature_ModelActivity temperature_modelActivity) {
    TextView tempname = holder.namemeasure;
    tempname.setText("Temperature");
    TextView tempval = holder.val;
   tempval.setText();

}

    @Override
    public int getItemCount() {
        return setdata.size();
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
public Temperature_AdapterActivity(ArrayList<Temperature_ModelActivity> data)
{
    this.setdata = data;
}*/

}
