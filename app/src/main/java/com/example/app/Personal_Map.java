package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class Personal_Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds Factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Factory=new LatLngBounds(new LatLng(40.989233,39.771122), new LatLng(40.989409,39.769852));
        //mMap.setLatLngBoundsForCameraTarget(Factory);

        // Add a marker in Teknokent and move the camera
        LatLng client = new LatLng(40.989409, 39.769852);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        String snippet = String.format(Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                client.latitude,
                client.longitude);

        mMap.addMarker(new MarkerOptions()
                .position(client)
                .title("Client")
                .snippet(snippet));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(client, 16.0f));

    }


}
