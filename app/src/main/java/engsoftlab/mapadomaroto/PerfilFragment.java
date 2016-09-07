package engsoftlab.mapadomaroto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;



public class PerfilFragment extends Fragment {
    private ProfilePictureView profilePictureView;
    private TextView txt;
    private TextView txtNome;
    private TextView txtSexo;
    private TextView txtNascimento;
    private TextView txtEmail;

    public static PerfilFragment newInstance(){
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }



    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_perfil,container,false);

        txt = (TextView) root.findViewById(R.id.txtTexto);
        txtNome = (TextView) root.findViewById(R.id.txtNome);
        txtSexo = (TextView) root.findViewById(R.id.txtSexo);
        txtNascimento = (TextView) root.findViewById(R.id.txtNascimento);
        txtEmail = (TextView) root.findViewById(R.id.txtEmail);

        profilePictureView = (ProfilePictureView) root.findViewById(R.id.profilePicture);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        profilePictureView.setProfileId(sharedPref.getString("userId", ""));
        txt.setText(sharedPref.getString("userId", ""));
        txtNome.setText(sharedPref.getString("name", ""));
        txtSexo.setText(sharedPref.getString("sexo", ""));
        txtNascimento.setText(sharedPref.getString("nasc", ""));
        txtEmail.setText(sharedPref.getString("email", ""));


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        return root;
    }

}
