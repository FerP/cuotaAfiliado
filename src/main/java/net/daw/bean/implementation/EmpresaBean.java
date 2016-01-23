package net.daw.bean.implementation;

import net.daw.bean.publicinterface.GenericBean;
import com.google.gson.annotations.Expose;
import static com.sun.jndi.toolkit.dir.SearchFilter.format;
import static com.sun.xml.internal.bind.unmarshaller.Messages.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
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

    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;

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

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "cif:" + cif + ",";
        strJson += "alta:" + alta + ",";
        strJson += "cambio:" + cambio + ",";
        strJson += "contacto:" + contacto + ",";
        strJson += "telefono:" + telefono + ",";
        

        if (expand) {
            strJson += "obj_usuario:" + obj_usuario.toJson(false) + ",";

        } else {
            strJson += "id_usuario:" + id_usuario + ",";

        }
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
        strColumns += "telefono,";
        strColumns += "id_usuario";

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
        strColumns += telefono + ",";
        strColumns += id_usuario;
        
        

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
        strPairs += "telefono=" + telefono + ",";
        strPairs += "id_usuario=" + id_usuario;

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
       if (expand > 0) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            UsuarioDao oUsuarioDao = new UsuarioDao(pooledConnection);
            oUsuarioBean.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean, expand - 1);
            this.setObj_usuario(oUsuarioBean);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
       
      
        return this;
    }

    
}
