package engsoftlab.mapadomaroto;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaFragment extends Fragment implements OnMapReadyCallback ,OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener, View.OnClickListener{
    private SupportMapFragment mapFragment;
    //private LinearLayout superContainer;
    private Marker marker;
    private GoogleMap map;
    private TextView mTapTextView;
    private Button addButton;
    private Button seeButton;
    private Dialog myDialog;

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

        View root = inflater.inflate(R.layout.fragment_mapa,container,false);
        //superContainer = (LinearLayout)root.findViewById(R.id.SuperContainer);

        mTapTextView = (TextView) root.findViewById(R.id.tap_text);
        addButton = (Button) root.findViewById(R.id.addButton);
        seeButton = (Button) root.findViewById(R.id.seeButton);
        addButton.setOnClickListener(this);

        mapFragment = SupportMapFragment.newInstance(options);
        mapFragment.getMapAsync(this);

        //map = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.fragment);
        //mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment);
        //mapFragment.getMapAsync(this);



        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.llContainer,mapFragment);
        ft.commit();

        //return inflater.inflate(R.layout.fragment_mapa, container, false);
        return root;
    }



    public void addButtonMtd (){
        if (marker == null) {
            Toast.makeText(getContext(), "Primeiro marque um ponto no Mapa", Toast.LENGTH_SHORT).show();
            Log.i("Script","addbutton");
        } else {
            callDialog();
            Toast.makeText(getContext(), "Ponto Marcado Lat: " + marker.getPosition().latitude + " Lng: " + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        map.setOnMapClickListener(this);
        map.setOnMapLongClickListener(this);
        map.setOnCameraIdleListener(this);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.4749331,-48.4555419),15);
        map.moveCamera(update);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.animateCamera(update, 5000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void customAddMarker(LatLng latlng, String title, String snippet){
        MarkerOptions options = new MarkerOptions();
        options.position(latlng).title(title).snippet(snippet);
        marker = map.addMarker(options);
        //map.addMarker(options);
        //marker = null;
    }

    public void customAddStaticMarker(LatLng latlng, String title, String snippet){
        MarkerOptions options = new MarkerOptions();
        options.position(latlng).title(title).snippet(snippet);
        //marker = map.addMarker(options);
        map.addMarker(options);
        //marker = null;
    }


    @Override
    public void onCameraIdle() {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        mTapTextView.setText("tapped, point=" + latLng.latitude+" "+latLng.longitude);
        Log.i("Script","tapped, point=" + latLng);
        if(marker !=null){
            marker.remove();
        }
        customAddMarker(new LatLng(latLng.latitude,latLng.longitude),"", "");
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addButton:addButtonMtd();break;
        }
    }


    public void callDialog()
    {
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dialog_form_layout);
        Button btnCancelar = (Button) myDialog.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                marker.remove();
            }
        });

        Button btnSalvar = (Button) myDialog.findViewById(R.id.btnSalvar);
        final EditText edtDesc = (EditText) myDialog.findViewById(R.id.edtDesc);
        final EditText  edtTipo = (EditText) myDialog.findViewById(R.id.edtTipo);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Irá pegar as informações e salva-las no servidor",Toast.LENGTH_SHORT).show();
                customAddStaticMarker(new LatLng(marker.getPosition().latitude,marker.getPosition().longitude),edtTipo.getText().toString(),"Descrição: "+edtDesc.getText().toString());
                myDialog.dismiss();
                marker.remove();
                marker = null;
            }
        });

        myDialog.setCancelable(true);
        myDialog.show();
    }


}
