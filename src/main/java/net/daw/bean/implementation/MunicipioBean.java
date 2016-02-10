package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.ComarcaDao;
import net.daw.dao.implementation.CuotaDao;
import net.daw.dao.implementation.MunicipioDao;
import net.daw.dao.implementation.ReciboDao;
import net.daw.dao.implementation.ProvinciaDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class MunicipioBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";

     
    @Expose(serialize = false)
    private Integer id_provincia = 0;
    
    @Expose(deserialize = false)
    private ProvinciaBean obj_provincia = null;
    
    @Expose(serialize = false)
    private Integer id_comarca = 0;
    
    @Expose(deserialize = false)
    private ComarcaBean obj_comarca = null;
    
    
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


    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public ProvinciaBean getObj_provincia() {
        return obj_provincia;
    }

    public void setObj_provincia(ProvinciaBean obj_provincia) {
        this.obj_provincia = obj_provincia;
    }

    public Integer getId_comarca() {
        return id_comarca;
    }

    public void setId_comarca(Integer id_comarca) {
        this.id_comarca = id_comarca;
    }

    public ComarcaBean getObj_comarca() {
        return obj_comarca;
    }

    public void setObj_comarca(ComarcaBean obj_comarca) {
        this.obj_comarca = obj_comarca;
    }

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "id_provincia:" + id_provincia + ",";
        strJson += "id_comarca:" + id_comarca;
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
         strColumns += "nombre,";
        strColumns += "id_provincia,";
        strColumns += "id_comarca";
        

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre)+",";
        strColumns += id_provincia + ",";
        strColumns += id_comarca;
        

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre)+",";
        strPairs += "id_provincia=" + id_provincia + ",";
        strPairs += "id_comarca=" + id_comarca;
        return strPairs;
    }

    @Override
    public MunicipioBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setId_provincia(oResultSet.getInt("id_provincia"));
        this.setId_comarca(oResultSet.getInt("id_comarca"));
       
if (expand > 0) {
            ProvinciaBean oProvinciaBean = new ProvinciaBean();
            ProvinciaDao oProvinciaDao = new ProvinciaDao(pooledConnection);
            oProvinciaBean.setId(oResultSet.getInt("id_provincia"));
            oProvinciaBean = oProvinciaDao.get(oProvinciaBean, expand - 1);
            this.setObj_provincia(oProvinciaBean);
        } else {
            this.setId_provincia(oResultSet.getInt("id_provincia"));
        }
       
       
       if (expand > 0) {
            ComarcaBean oComarcaBean = new ComarcaBean();
            ComarcaDao oComarcaDao = new ComarcaDao(pooledConnection);
            oComarcaBean.setId(oResultSet.getInt("id_comarca"));
            oComarcaBean = oComarcaDao.get(oComarcaBean, expand - 1);
            this.setObj_comarca(oComarcaBean);
        } else {
            this.setId_comarca(oResultSet.getInt("id_comarca"));
        }
        
   return this;

    }

}

   


