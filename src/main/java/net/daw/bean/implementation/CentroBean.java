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

public class CentroBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    @Expose
    private String direccion = "";
    @Expose
    private String cp = "";
    @Expose
    private Integer telefono = 0;
    
    @Expose(serialize = false)
    private Integer id_empresa = 0;
    @Expose(deserialize = false)
    private EmpresaBean obj_empresa = null;
    
    @Expose(serialize = false)
    private Integer id_municipio = 0;
    @Expose(deserialize = false)
    private MunicipioBean obj_municipio = null;
    
    @Expose(serialize = false)
    private Integer id_sector = 0;
    @Expose(deserialize = false)
    private SectorBean obj_sector = null;
    
    

    public CentroBean() {
        this.id = 0;
    }

    public CentroBean(Integer id) {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public EmpresaBean getObj_empresa() {
        return obj_empresa;
    }

    public void setObj_empresa(EmpresaBean obj_empresa) {
        this.obj_empresa = obj_empresa;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public MunicipioBean getObj_municipio() {
        return obj_municipio;
    }

    public void setObj_municipio(MunicipioBean obj_municipio) {
        this.obj_municipio = obj_municipio;
    }

    public Integer getId_sector() {
        return id_sector;
    }

    public void setId_sector(Integer id_sector) {
        this.id_sector = id_sector;
    }

    public SectorBean getObj_sector() {
        return obj_sector;
    }

    public void setObj_sector(SectorBean obj_sector) {
        this.obj_sector = obj_sector;
    }


    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "direccion:" + direccion + ",";
        strJson += "cp:" + cp + ",";
        strJson += "telefono:" + telefono + ",";
        if (expand) {
            strJson += "obj_empresa:" + obj_empresa.toJson(false) + ",";

        } else {
            strJson += "id_empresa:" + id_empresa + ",";

        }
        
        if (expand) {
            strJson += "obj_municipio:" + obj_municipio.toJson(false) + ",";

        } else {
            strJson += "id_municipio:" + id_municipio + ",";

        }
        
        if (expand) {
            strJson += "obj_sector:" + obj_sector.toJson(false) + ",";

        } else {
            strJson += "id_sector:" + id_sector + ",";

        }
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "direccion,";
        strColumns += "cp";
        strColumns += "telefono";
        strColumns += "id_empresa";
        strColumns += "id_municipio";
        strColumns += "id_sector";
       
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += nombre + ",";
        strColumns += direccion + ",";      
        strColumns += cp + ",";
        strColumns += telefono + ",";
        strColumns += id_empresa + ",";
        strColumns += id_municipio + ",";
        strColumns += id_sector ;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "nombre=" + nombre + ",";
        strPairs += "direccion=" + direccion + ",";
        strPairs += "cp=" + cp + ",";
        strPairs += "telefono=" + telefono + ",";
        strPairs += "id_empresa=" + id_empresa + ",";
        strPairs += "id_municipio=" + id_municipio + ",";
        strPairs += "id_sector=" + id_sector;
        return strPairs;
    }

    @Override
    public CentroBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setDireccion(oResultSet.getString("direccion"));
        this.setCp(oResultSet.getString("cp"));
        this.setTelefono(oResultSet.getInt("telefono"));
        
        
         if (expand > 0) {
            EmpresaBean oEmpresaBean = new EmpresaBean();
            EmpresaDao oEmpresaDao = new EmpresaDao(pooledConnection);
            oEmpresaBean.setId(oResultSet.getInt("id_empresa"));
            oEmpresaBean = oEmpresaDao.get(oEmpresaBean, expand - 1);
            this.setObj_empresa(oEmpresaBean);
        } else {
            this.setId_empresa(oResultSet.getInt("id_empresa"));
        }
         
         if (expand > 0) {
            MunicipioBean oMunicipioBean = new MunicipioBean();
            MunicipioDao oMunicipioDao = new MunicipioDao(pooledConnection);
            oMunicipioBean.setId(oResultSet.getInt("id_municipio"));
            oMunicipioBean = oMunicipioDao.get(oMunicipioBean, expand - 1);
            this.setObj_municipio(oMunicipioBean);
        } else {
            this.setId_municipio(oResultSet.getInt("id_municipio"));
        }
         
         if (expand > 0) {
            SectorBean oSectorBean = new SectorBean();
            SectorDao oSectorDao = new SectorDao(pooledConnection);
            oSectorBean.setId(oResultSet.getInt("id_sector"));
            oSectorBean = oSectorDao.get(oSectorBean, expand - 1);
            this.setObj_sector(oSectorBean);
        } else {
            this.setId_sector(oResultSet.getInt("id_sector"));
        }
        return this;

    }

}
