package com.demo.saulromero.demobran;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.saulromero.demobran.Util.InputFilterMinMax;
import com.demo.saulromero.demobran.db.DatabaseHelpher;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button button,button2,button3;
    private TextView textView;
    private  LatLng sydney = new LatLng(-34, 151);
    private DatabaseHelpher helpher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button =(Button) findViewById(R.id.btn);
        button2 =(Button) findViewById(R.id.btn2);
        button3 =(Button) findViewById(R.id.btn3);
        textView= (TextView) findViewById(R.id.editText);

        textView.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10000")});

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomLocation(sydney, 100,Integer.parseInt(textView.getText().toString()));


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goActivity2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goActivity3();
            }
        });


        getDB();
    }

 private void goActivity2(){

     Intent intent = new Intent(this, MainActivity2.class);
     startActivity(intent);

 }


    private void goActivity3(){

        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }




    public LatLng getRandomLocation(LatLng point, int radius,int steps) {

        List<LatLng> randomPoints = new ArrayList<>();
        List<Float> randomDistances = new ArrayList<>();
        Location myLocation = new Location("");
        myLocation.setLatitude(point.latitude);
        myLocation.setLongitude(point.longitude);
        LatLng randomLatLng= null;

        //This is to generate 10 random points
        for(int i = 0; i<steps; i++) {
            double x0 = point.latitude;
            double y0 = point.longitude;

            Random random = new Random();

            // Convert radius from meters to degrees
            double radiusInDegrees = radius / 111000f;

            double u = random.nextDouble();
            double v = random.nextDouble();
            double w = radiusInDegrees * Math.sqrt(u);
            double t = 2 * Math.PI * v;
            double x = w * Math.cos(t);
            double y = w * Math.sin(t);

            // Adjust the x-coordinate for the shrinking of the east-west distances
            double new_x = x / Math.cos(y0);

            double foundLatitude = new_x + x0;
            double foundLongitude = y + y0;
            randomLatLng = new LatLng(foundLatitude, foundLongitude);
            randomPoints.add(randomLatLng);
            Location l1 = new Location("");
            l1.setLatitude(randomLatLng.latitude);
            l1.setLongitude(randomLatLng.longitude);
            randomDistances.add(l1.distanceTo(myLocation));

            mMap.addMarker(new MarkerOptions().position(randomLatLng).title(""));


        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(randomLatLng));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        //Get nearest point to the centre
        int indexOfNearestPointToCentre =
                randomDistances.indexOf(Collections.min(randomDistances));
        return randomPoints.get(indexOfNearestPointToCentre);
    }


    private void getDB(){

        helpher = new DatabaseHelpher(getApplicationContext());
        helpher.insertIntoEmpDB("Miguel cervantes","Desarrollador","08/12/1990");
        helpher.insertIntoEmpDB("Juan morales","Desarrollador","03/07/1990");
        helpher.insertIntoEmpDB("Roberto Mendez","Desarrollador","14/09/1990");
        helpher.insertIntoEmpDB("Miguel cuevas","Desarrollador","08/12/1990");
        helpher.closeDB();



    }

}
