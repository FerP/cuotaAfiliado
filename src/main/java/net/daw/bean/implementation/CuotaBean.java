package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.helper.statics.EncodingUtilHelper;

public class CuotaBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String tipocuota = "";
     @Expose
    private double importe;

    public CuotaBean() {
        this.id = 0;
    }

    public CuotaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipocuota() {
        return tipocuota;
    }

    public void setTipocuota(String tipocuota) {
        this.tipocuota = tipocuota;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

   

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "tipocuota:" + tipocuota + ",";  
        strJson += "importe:" + importe; 
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "tipocuota,";
        strColumns += "importe";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(tipocuota) + ",";
        strColumns += importe;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "tipocuota=" + EncodingUtilHelper.quotate(tipocuota) + ",";
        strPairs += "importe=" + importe;

        return strPairs;
    }

    @Override
    public CuotaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setTipocuota(oResultSet.getString("tipocuota"));
        this.setImporte(oResultSet.getDouble("importe"));
        return this;

    }

}


