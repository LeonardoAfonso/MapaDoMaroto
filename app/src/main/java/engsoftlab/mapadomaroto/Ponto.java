package engsoftlab.mapadomaroto;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by AEDI on 12/09/16.
 */
public class Ponto {
    private LatLng latLng;
    private int id;
    private String tipo;
    private String descricao;
    private String datacriacao;
    private String usercriacao;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(String datacriacao) {
        this.datacriacao = datacriacao;
    }

    public String getUsercriacao() {
        return usercriacao;
    }

    public void setUsercriacao(String usercriacao) {
        this.usercriacao = usercriacao;
    }

    private String estado;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



}
