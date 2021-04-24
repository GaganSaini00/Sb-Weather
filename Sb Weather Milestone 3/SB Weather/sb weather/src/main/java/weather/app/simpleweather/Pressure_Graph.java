package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Pressure_Graph extends AppCompatActivity {
    GraphView graphView;
    DatabaseReference ref;
    LineGraphSeries lineGraphSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure__graph);

        graphView = findViewById(R.id.Pressure_graph);
        ref = FirebaseDatabase.getInstance().getReference().child("NathanSensor");
        lineGraphSeries= new LineGraphSeries();
        graphView.addSeries(lineGraphSeries);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for(DataSnapshot mysnapshot : snapshot.getChildren())
                {
                    Pressure_Model pressure_model = mysnapshot.getValue(Pressure_Model.class);
                    dp[index] = new DataPoint(index,pressure_model.getAirpressure());
                    index++;
                }
                lineGraphSeries.resetData(dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}