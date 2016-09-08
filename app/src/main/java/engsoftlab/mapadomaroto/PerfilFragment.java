package engsoftlab.mapadomaroto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;



public class PerfilFragment extends Fragment implements View.OnClickListener{
    private ProfilePictureView profilePictureView;
    private TextView txt;
    private TextView txtNome;
    private TextView txtSexo;
    private TextView txtNascimento;
    private TextView txtEmail;
    private LoginButton logoutButton;

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
        FacebookSdk.sdkInitialize(getContext());

        View root = inflater.inflate(R.layout.fragment_perfil,container,false);

        txt = (TextView) root.findViewById(R.id.txtTexto);
        txtNome = (TextView) root.findViewById(R.id.txtNome);
        txtSexo = (TextView) root.findViewById(R.id.txtSexo);
        txtNascimento = (TextView) root.findViewById(R.id.txtNascimento);
        txtEmail = (TextView) root.findViewById(R.id.txtEmail);

        profilePictureView = (ProfilePictureView) root.findViewById(R.id.profilePicture);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        profilePictureView.setProfileId(sharedPref.getString("userId", ""));
        //txt.setText(sharedPref.getString("userId", ""));
        txtNome.setText("Nome: "+sharedPref.getString("name",  null));
        txtEmail.setText("Email :"+sharedPref.getString("email",  null));
        txtSexo.setText("Sexo :"+sharedPref.getString("sexo", null));
        txtNascimento.setText("Nascimento :"+sharedPref.getString("nasc", null));

        logoutButton = (LoginButton) root.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout_button:
                LoginManager.getInstance().logOut();
                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
        }
    }
}
