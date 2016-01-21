package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.helper.statics.EncodingUtilHelper;


public class EmpresaBean implements GenericBean {

    @Expose
    private Integer id;
    
    @Expose
    private String nombre = "";
    @Expose

    private String cif = "";
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
        strColumns += EncodingUtilHelper.quotate(contacto) + ",";
        strColumns += telefono + ",";
        strColumns += id_usuario;
        
        

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";     
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "cif=" + EncodingUtilHelper.quotate(cif) + ",";
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
