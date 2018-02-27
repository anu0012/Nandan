package bumblebee.nandan;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapMedi extends FragmentActivity implements OnMapReadyCallback,GeoQueryEventListener, GoogleMap.OnCameraChangeListener {

    private static final GeoLocation INITIAL_CENTER = new GeoLocation(28.501497, 77.1638482);
    private static final int INITIAL_ZOOM_LEVEL = 1;
    private static final String GEO_FIRE_DB = "https://publicdata-transit.firebaseio.com";
    private static final String GEO_FIRE_REF = GEO_FIRE_DB + "/_geofire";

    private GoogleMap map;
    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;

    private Map<String,Marker> markers;
    ArrayList<Hospital> hospitals = new ArrayList<>();
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_medi);
        hospitals = (ArrayList<Hospital>) getIntent().getSerializableExtra("objects");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        this.map = mapFragment.getMap();
//        LatLng latLngCenter = new LatLng(INITIAL_CENTER.latitude, INITIAL_CENTER.longitude);
//        this.searchCircle = this.map.addCircle(new CircleOptions().center(latLngCenter).radius(1000));
//        this.searchCircle.setFillColor(Color.argb(66, 255, 0, 255));
//        this.searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
//        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, INITIAL_ZOOM_LEVEL));
//        this.map.setOnCameraChangeListener(this);

//        FirebaseOptions options = new FirebaseOptions.Builder().setApplicationId("geofire").setDatabaseUrl(GEO_FIRE_DB).build();
//        FirebaseApp app = FirebaseApp.initializeApp(this, options);

        // setup GeoFire
        //this.geoFire = new GeoFire(FirebaseDatabase.getInstance(app).getReferenceFromUrl(GEO_FIRE_REF));
        // radius in km
        //this.geoQuery = this.geoFire.queryAtLocation(INITIAL_CENTER, 1);

        // setup markers
        this.markers = new HashMap<String, Marker>();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // remove all event listeners to stop updating in the background
        //this.geoQuery.removeAllListeners();
        for (Marker marker: this.markers.values()) {
            marker.remove();
        }
        this.markers.clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // add an event listener to start updating locations again
        //this.geoQuery.addGeoQueryEventListener(this);
    }

    @Override
    public void onKeyEntered(String key, GeoLocation location) {
        // Add a new marker to the map
        Marker marker = this.map.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude)));
        this.markers.put(key, marker);
    }

    @Override
    public void onKeyExited(String key) {
        // Remove any old marker
        Marker marker = this.markers.get(key);
        if (marker != null) {
            marker.remove();
            this.markers.remove(key);
        }
    }

    @Override
    public void onKeyMoved(String key, GeoLocation location) {
        // Move the marker
        Marker marker = this.markers.get(key);
        if (marker != null) {
            this.animateMarkerTo(marker, location.latitude, location.longitude);
        }
    }

    @Override
    public void onGeoQueryReady() {
    }

    @Override
    public void onGeoQueryError(DatabaseError error) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("There was an unexpected error querying GeoFire: " + error.getMessage())
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    // Animation handler for old APIs without animation support
    private void animateMarkerTo(final Marker marker, final double lat, final double lng) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long DURATION_MS = 3000;
        final Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final LatLng startPosition = marker.getPosition();
        handler.post(new Runnable() {
            @Override
            public void run() {
                float elapsed = SystemClock.uptimeMillis() - start;
                float t = elapsed/DURATION_MS;
                float v = interpolator.getInterpolation(t);

                double currentLat = (lat - startPosition.latitude) * v + startPosition.latitude;
                double currentLng = (lng - startPosition.longitude) * v + startPosition.longitude;
                marker.setPosition(new LatLng(currentLat, currentLng));

                // if animation is not finished yet, repeat
                if (t < 1) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    private double zoomLevelToRadius(double zoomLevel) {
        // Approximation to fit circle into view
        return 16384000/Math.pow(2, zoomLevel);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        // Update the search criteria for this geoQuery and the circle on the map
        LatLng center = cameraPosition.target;
        double radius = zoomLevelToRadius(cameraPosition.zoom);
        this.searchCircle.setCenter(center);
        this.searchCircle.setRadius(radius);
        //this.geoQuery.setCenter(new GeoLocation(center.latitude, center.longitude));
        // radius in km
        //this.geoQuery.setRadius(radius/1000);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Toast.makeText(MapMedi.this, hospitals.size()+"", Toast.LENGTH_SHORT).show();
        this.map = googleMap;
        for(int i=0;i<hospitals.size();i++)
        {
            LatLng loc = new LatLng(Double.parseDouble(hospitals.get(i).getLatitide()), Double.parseDouble(hospitals.get(i).getLongitude()));
            if(hospitals.get(i).getFacilities().contains("Blood Bank"))
            {
                googleMap.addMarker(new MarkerOptions().position(loc)
                        .title(hospitals.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.blood_drop_icon));
            }
            else{
                googleMap.addMarker(new MarkerOptions().position(loc)
                        .title(hospitals.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.hospital_pointer));
            }

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

        LatLng latLngCenter = new LatLng(INITIAL_CENTER.latitude, INITIAL_CENTER.longitude);
        this.searchCircle = this.map.addCircle(new CircleOptions().center(latLngCenter).radius(100));
        this.searchCircle.setFillColor(Color.argb(66, 255, 0, 255));
        this.searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, INITIAL_ZOOM_LEVEL));
        this.map.setOnCameraChangeListener(this);

//        CircleOptions circleOptions = new CircleOptions()
//                .center(new LatLng(11.4, 92.7))
//                .radius(1000).strokeWidth(10)
//                .strokeColor(Color.BLACK)
//                .fillColor(Color.argb(0, 0, 0, 0))
//                .clickable(true); // In meters
//
//        // Get back the mutable Circle
//        Circle circle = googleMap.addCircle(circleOptions);
//
//        googleMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
//
//            @Override
//            public void onCircleClick(Circle circle) {
//                // Flip the r, g and b components of the circle's
//                // stroke color.
//                int strokeColor = circle.getStrokeColor() ^ 0x00ffffff;
//                circle.setStrokeColor(strokeColor);
//            }
//        });

    }

}
