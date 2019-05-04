package si.povhe.symbollayer_kovac;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.Style;

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
        mapView.getMapAsync(mapboxMap -> {


            mapboxMap.setOnMarkerClickListener(marker -> {
                FragmentManager manager = getSupportFragmentManager();
                Fragment fragment = manager.findFragmentById(R.id.fragment_container);

                Bundle packet = new Bundle();
                String markerName = marker.getTitle();
                String markerOpis = marker.getSnippet();
                int markerImage=R.drawable.cerkev;

                switch (markerName) {
                    case "Ambruški vaški center":
                        markerImage = R.drawable.ambrus;
                        break;
                    case "Kraška jama":
                        markerImage = R.drawable.jama;
                        break;
                    case "Ciganov Vrh, ruševine":
                        markerImage = R.drawable.ciganov;
                        break;
                    case "Izvir reke Krke":
                        markerImage = R.drawable.krka;
                        break;
                    case "Cerkev Marije Brezmadežne":
                        markerImage = R.drawable.cerkev;
                        break;
                }
                packet.putString("marker_name", markerName);
                packet.putInt("marker_image", markerImage);
                packet.putString("marker_opis", markerOpis);
                System.out.println("Packet:"+markerName);
                MarkerInfo sendData = new MarkerInfo();
                sendData.setArguments(packet);
                if(fragment!=null){
                    manager.popBackStackImmediate();
                }
                fragment = new MarkerInfo();
                fragment.setArguments(packet);
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                return true;
            });

            mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(45.828328, 14.816625))
                        .title(getString(R.string.marker1)))
                        .setSnippet(getString(R.string.ambrus_opis));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(45.858274, 14.835290))
                        .title(getString(R.string.marker2)))
                        .setSnippet(getString(R.string.cerkev_opis));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(45.889839, 14.771099))
                        .title(getString(R.string.marker3)))
                        .setSnippet(getString(R.string.krka_opis));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(45.856914, 14.833240))
                        .title(getString(R.string.marker4)))
                        .setSnippet(getString(R.string.jama_opis));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(45.846708, 14.779842))
                        .title(getString(R.string.marker5)))
                        .setSnippet(getString(R.string.vrh_opis));

            });
        });
    }

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