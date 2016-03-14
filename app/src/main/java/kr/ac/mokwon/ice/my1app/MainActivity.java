package kr.ac.mokwon.ice.my1app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView tx_lat, tx_long, tx_alt, tx_state;
    public Button bt_loc, bt_map;
    public LocationManager locationMan;
    public MyLocationListener myLocationLx;
    public double latitude, longitude, altitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx_lat = (TextView) findViewById(R.id.tx_lat);
        tx_long = (TextView) findViewById(R.id.tx_long);
        tx_alt = (TextView) findViewById(R.id.tx_alt);
        tx_state = (TextView) findViewById(R.id.tx_state);
        bt_loc = (Button) findViewById(R.id.bt_loc);
        bt_map = (Button) findViewById(R.id.bt_map);


        locationMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        myLocationLx = new MyLocationListener();
        long minTime = 1000;
        long minDistance = 0;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, myLocationLx);

        bt_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitude = myLocationLx.latitude;
                longitude = myLocationLx.longitude;
                altitude = myLocationLx.altitude;
                tx_lat.setText(String.format("%g", latitude));
                tx_long.setText(String.format("%g", longitude));
                tx_alt.setText(String.format("%g", altitude));
            }
        });

        bt_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:%g,%g,%g?z=17", latitude, longitude, altitude)));

                startActivity(intent);
            }
        });


    }
}