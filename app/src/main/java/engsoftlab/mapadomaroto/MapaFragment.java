package engsoftlab.mapadomaroto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaFragment extends Fragment implements OnMapReadyCallback{
    private SupportMapFragment mapFragment;


    public static MapaFragment newInstance(){
        MapaFragment fragment = new MapaFragment();
        return fragment;
    }



    public MapaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GoogleMapOptions options = new GoogleMapOptions();
        options.zOrderOnTop(true);
        mapFragment = SupportMapFragment.newInstance(options);
        mapFragment.getMapAsync(this);
        //mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        //mapFragment.getMapAsync(this);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.llContainer,mapFragment);
        ft.commit();

        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.4749331,-48.4555419),15);
        googleMap.moveCamera(update);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.animateCamera(update, 5000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });
    }
}
