package weather.app.simpleweather;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.mainViewHolder> {
private ArrayList<Model> setdata;

Context context;

public Adapter(ArrayList<Model> weatherinfo, Activity activity)
{

    this.setdata = weatherinfo;
    this.context = activity;
}

    @NonNull
    @Override
    public mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page,parent,false);
        view.setOnClickListener(HomeActivity.myOnClickListener);
        mainViewHolder mainholder = new mainViewHolder(view);
        return mainholder;

    }

        @Override
        public void onBindViewHolder(@NonNull mainViewHolder holder, int position) {
    TextView tempname = holder.namemeasure;
    TextView tempval = holder.val;
    tempname.setText(setdata.get(position).getWeatherName());
   tempval.setText(setdata.get(position).getWeatherval());

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
public Adapter(ArrayList<Model> data)
{
    this.setdata = data;
}

}
