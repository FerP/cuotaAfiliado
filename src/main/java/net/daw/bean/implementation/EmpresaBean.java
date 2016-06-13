package net.daw.bean.implementation;

import net.daw.bean.publicinterface.GenericBean;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;



public class EmpresaBean implements GenericBean {

    @Expose
    private Integer id;
    
    @Expose
    private String nombre = "";
    @Expose
    private String cif = "";
       @Expose
    private Date alta = new Date();
    @Expose
    private Date cambio = new Date();
    @Expose
    private String contacto = "";
    @Expose
    private Integer telefono = 0;

    public EmpresaBean() {
        this.id = 0;
    }

    public EmpresaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getCambio() {
        return cambio;
    }

    public void setCambio(Date cambio) {
        this.cambio = cambio;
    }

   

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }


    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }


    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + EncodingUtilHelper.quotate(nombre) + ",";
        strJson += "cif:" + EncodingUtilHelper.quotate(cif) + ",";
        strJson += "alta:" + alta + ",";
        strJson += "cambio:" + cambio + ",";
        strJson += "contacto:" + contacto + ",";
        strJson += "telefono:" + telefono;
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";      
        strColumns += "nombre,";
        strColumns += "cif,";
        strColumns += "alta,";
        strColumns += "cambio,";
        strColumns += "contacto,";
        strColumns += "telefono";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ","; 
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += EncodingUtilHelper.quotate(cif) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(alta) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(cambio) + ",";
        strColumns += EncodingUtilHelper.quotate(contacto) + ",";
        strColumns += telefono;

        return strColumns;
    }

    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strPairs = "";
        strPairs += "id=" + id + ",";     
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "cif=" + EncodingUtilHelper.quotate(cif) + ",";
        strPairs += "alta=" + EncodingUtilHelper.quotate(format.format(alta)) + ",";
        strPairs += "cambio=" + EncodingUtilHelper.quotate(format.format(cambio)) + ",";
        strPairs += "contacto=" + EncodingUtilHelper.quotate(contacto) + ",";
        strPairs += "telefono=" + telefono;

        return strPairs;
    }

    @Override
     public EmpresaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setCif(oResultSet.getString("cif"));
        this.setAlta(oResultSet.getDate("alta"));
       this.setCambio(oResultSet.getDate("cambio"));
        this.setContacto(oResultSet.getString("contacto"));
        this.setTelefono(oResultSet.getInt("telefono"));
       
      
        return this;
    }

    
}
