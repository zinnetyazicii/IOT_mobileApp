package com.example.app;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class General_Map extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        //double[] Lat = new double[]{ 40.989400, 40.989425, 40.989455, 40.989476, 40.989500};

        double[] Lat = new double[]{ 40.939202, 40.934230, 40.93572, 40.93729, 40.93639};

        // Add markers in Teknokent and move the camera
        for (int i = 0; i < 5; i++) {
            LatLng client = new LatLng(Lat[i], 39.769852);
            String snippet = String.format(Locale.getDefault(),
                    "Lat: %1$.5f, Long: %2$.5f",
                    client.latitude,
                    client.longitude);

            mMap.addMarker(new MarkerOptions()
                    .position(client)
                    .title("Client")
                    .snippet(snippet));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(client, 16.f));
        }

    }
}
