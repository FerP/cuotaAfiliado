package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.dao.implementation.MunicipioDao;
import net.daw.dao.implementation.SectorDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class ReciboBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String descripcion = "";
    @Expose
    private Integer emision;
    @Expose
    private String periodo = "";

    public ReciboBean() {
        this.id = 0;
    }

    public ReciboBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getEmision() {
        return emision;
    }

    public void setEmision(Integer emision) {
        this.emision = emision;
    }

    public String getClaverecibo() {
        return periodo;
    }

    public void setClaverecibo(String periodo) {
        this.periodo = periodo;
    }
    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "descripcion:" + descripcion + ",";
        strJson += "emision:" + emision + ",";
        strJson += "periodo:" + periodo;
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "descripcion,";
        strColumns += "emision,";
        strColumns += "periodo";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(descripcion) + ",";
        strColumns += emision + ",";
        strColumns += EncodingUtilHelper.quotate(periodo);
        
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "descripcion=" + EncodingUtilHelper.quotate(descripcion)+ ",";
        strPairs += "emision=" + emision + ",";
        strPairs += "periodo=" + EncodingUtilHelper.quotate(periodo);
        return strPairs;
    }

    @Override
    public ReciboBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setEmision(oResultSet.getInt("emision"));
        this.setClaverecibo(oResultSet.getString("periodo"));
        return this;
    }
}
