package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Temperature_Graph extends AppCompatActivity {
    GraphView graphView;
    DatabaseReference ref;
    LineGraphSeries lineGraphSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature__graph);

        graphView = findViewById(R.id.Temperature_graph);
        ref = FirebaseDatabase.getInstance().getReference().child("GaganSensor");
        lineGraphSeries= new LineGraphSeries();
        graphView.addSeries(lineGraphSeries);

    }

    @Override
    protected void onStart() {
        super.onStart();
        GridLabelRenderer gridLabelRenderer = graphView.getGridLabelRenderer();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for(DataSnapshot mysnapshot : snapshot.getChildren())
                {
                    Temperature_Model temperature_model = mysnapshot.getValue(Temperature_Model.class);
                    dp[index] = new DataPoint(index,temperature_model.getTemperature());
                    index++;
                }
                lineGraphSeries.resetData(dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        graphView.setTitle("Temperature Graph");
        graphView.setTitleTextSize(70);
        graphView.setTitleColor(Color.BLACK);

        gridLabelRenderer.setHorizontalAxisTitle("Time");
        gridLabelRenderer.setHorizontalAxisTitleTextSize(70);
        gridLabelRenderer.setVerticalAxisTitle("Temperature");
        gridLabelRenderer.setVerticalAxisTitleTextSize(70);

    }


}