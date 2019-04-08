package si.povhe.symbollayer_kovac;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

public class MainActivity extends AppCompatActivity implements MarkerInfo.OnFragmentInteractionListener {

    private MapView mapView;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {


                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        FragmentManager manager = getSupportFragmentManager();
                        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

                        Bundle packet = new Bundle();
                        String markerName = marker.getTitle();
                        int markerImage=R.drawable.cerkev;
                        if (markerName.equals("Ambruški vaški center")){
                            markerImage = R.drawable.ambrus;
                        } else if (markerName.equals("Kraška jama")){
                            markerImage = R.drawable.jama;
                        } else if (markerName.equals("Ciganov Vrh, ruševine")){
                            markerImage = R.drawable.ciganov;
                        } else if (markerName.equals("Izvir reke Krke")){
                            markerImage  =R.drawable.krka;
                        } else if (markerName.equals("Cerkev Marije Brezmadežne")){
                            markerImage = R.drawable.cerkev3;
                        }
                        packet.putString("marker_name", markerName);
                        packet.putInt("marker_image", markerImage);
                        System.out.println("Packet:"+markerName);
                        MarkerInfo sendData = new MarkerInfo();
                        sendData.setArguments(packet);


                        fragment = new MarkerInfo();
                        fragment.setArguments(packet);
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.addToBackStack(null);

                        transaction.add(R.id.fragment_container, fragment).commit();



                        return true;
                    }
                });

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
// Add the marker image to map



                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(45.828328, 14.816625))
                                .title(getString(R.string.marker1)));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(45.858274, 14.835290))
                                .title(getString(R.string.marker2)));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(45.889839, 14.771099))
                                .title(getString(R.string.marker3)));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(45.856914, 14.833240))
                                .title(getString(R.string.marker4)));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(45.846708, 14.779842))
                                .title(getString(R.string.marker5)));



                    }
                });
            }
        });
    }

    // Add the mapView's own lifecycle methods to the activity's lifecycle methods
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}