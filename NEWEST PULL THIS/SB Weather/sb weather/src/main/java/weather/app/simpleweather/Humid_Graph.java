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

public class Humid_Graph extends AppCompatActivity {

    GraphView graphView;
    DatabaseReference ref;
    LineGraphSeries lineGraphSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humid__graph);

        graphView = findViewById(R.id.humid_graph);
        ref = FirebaseDatabase.getInstance().getReference().child("JeremySensor");
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
                    Humidity_Model humidity_model = mysnapshot.getValue(Humidity_Model.class);
                    dp[index] = new DataPoint(index,humidity_model.getHumidity());
                    index++;
                }
                lineGraphSeries.resetData(dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

/*        graphView.setTitle("Humidity Graph");
        graphView.setTitleTextSize(70);
        graphView.setTitleColor(Color.BLACK);

        gridLabelRenderer.setHorizontalAxisTitle("Time");
        gridLabelRenderer.setHorizontalAxisTitleTextSize(70);
        gridLabelRenderer.setVerticalAxisTitle("Humidity");
        gridLabelRenderer.setVerticalAxisTitleTextSize(70);*/

    }

}