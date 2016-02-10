package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.helper.statics.EncodingUtilHelper;

public class ProvinciaBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    

    public ProvinciaBean() {
        this.id = 0;
    }

    public ProvinciaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre; 
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre);

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre);

        return strPairs;
    }

    @Override
    public ProvinciaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        return this;

    }

}
