package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.dao.implementation.UsuarioDao;

public class MunicipioBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    @Expose
    private String provincia = "";
    @Expose
    private String comarca = "";
     
    @Expose(serialize = false)
    private Integer id_provincia = 0;
    @Expose(deserialize = false)
    private EmpresaBean obj_provincia = null;
    
    @Expose(serialize = false)
    private Integer id_comarca = 0;
    @Expose(deserialize = false)
    private MunicipioBean obj_comarca = null;
    
    
    
    

    public MunicipioBean() {
        this.id = 0;
    }

    public MunicipioBean(Integer id) {
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public EmpresaBean getObj_provincia() {
        return obj_provincia;
    }

    public void setObj_provincia(EmpresaBean obj_provincia) {
        this.obj_provincia = obj_provincia;
    }

    public Integer getId_comarca() {
        return id_comarca;
    }

    public void setId_comarca(Integer id_comarca) {
        this.id_comarca = id_comarca;
    }

    public MunicipioBean getObj_comarca() {
        return obj_comarca;
    }

    public void setObj_comarca(MunicipioBean obj_comarca) {
        this.obj_comarca = obj_comarca;
    }

   


    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "provincia:" + provincia + ",";
        strJson += "comarca:" + comarca + ",";
        strJson += "id_provincia:" + id_provincia + ",";
        strJson += "id_comarca:" + id_comarca + ",";
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
         strColumns += "nombre,";
        strColumns += "provincia,";
        strColumns += "comarca";
        strColumns += "id_provincia";
        strColumns += "id_comarca";
        

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += nombre + ",";
        strColumns += provincia + ",";      
        strColumns += comarca + ",";
        strColumns += id_provincia + ",";
        strColumns += id_comarca + ",";
        

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "nombre=" + nombre + ",";
        strPairs += "provincia=" + provincia+ ",";
        strPairs += "comarca=" + comarca + ",";
        strPairs += "id_provincia=" + id_provincia + ",";
        strPairs += "id_comarca=" + id_comarca;
        return strPairs;
    }

    @Override
    public MunicipioBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setProvincia(oResultSet.getString("provincia"));
        this.setComarca(oResultSet.getString("comarca"));
        this.setId_provincia(oResultSet.getInt("id_provincia"));
        this.setId_comarca(oResultSet.getInt("id_comarca"));
       
//        this.setComarca(oResultSet.getString("comarca"));
        
   return this;

    }

}

   


