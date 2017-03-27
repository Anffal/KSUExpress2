package com.example.anfal.ksuexpress;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    Button btnShowLocation;
    GPSTracker gps;

    private GoogleMap mMap;
    // ( 24.793280 ,  46.665184 )
    // University
    private final LatLng LOCATION = new LatLng(24.724926, 46.637564);


    // My home
    // private final LatLng LOCATION = new LatLng(24.707345, 46.812777);
    //private final LatLng LOCATION = new LatLng(24.724926, 46.637564);

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        LocationManager locationManager;
        LocationListener locationListner;


    } //end of onCreate method

    /*public void Start_button(GoogleMap map) {


        gps = new com.example.anfal.ksuexpress.GPSTracker(Map.this);
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Test Marker"));
        System.out.println("أهلا , تكفى ادخل هالميثود");
        //LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
        //mMap.addMarker(new MarkerOptions().position(loc).title("Driver"));
    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in KSU and move the camera
        //mMap.addMarker(new MarkerOptions().position(new LatLng(24.724926, 46.637564)).title("Marker in KSU"));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION,17);
        mMap.animateCamera(update);

        ///////////////////
        //////////////////

        btnShowLocation = (Button) findViewById(R.id.show_Location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new GPSTracker(Map.this);
                if (gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("Current Position");
                    // Changing marker icon
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker10);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).icon(icon));
                    System.out.println("تكفى اشتغل , داخل ميثود ON CLICK ");
                    System.out.println(latitude);
                    System.out.println(longitude);
                    System.out.println(gps.isNetworkEnabled);
                    System.out.println(gps.isGPSEnabled);
                    //System.out.println(gps.getLocation().getLatitude());

                    //Toast.makeText(getApplicationContext(),"Your Location is -\nLat: "+latitude+ "\nLong: "+ longitude,Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Map Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /** Called when the user clicks the nootifi button */
    public void openNotification(View view) {
        Intent intent = new Intent(this, Send_notification.class);
        startActivity(intent);
    }


    public void openAdminProfile(View view) {
        Intent intent = new Intent(this, Admin_profile.class);
        startActivity(intent);
    }

    public void openContact(View view) {
        Intent intent = new Intent(this, contact_us.class);
        startActivity(intent);
    }
}
