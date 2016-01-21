package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.AfiliadoDao;
import net.daw.dao.implementation.CentroDao;
import net.daw.dao.implementation.CuotaDao;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.dao.implementation.MunicipioDao;
import net.daw.dao.implementation.SectorDao;
import net.daw.dao.implementation.UsuarioDao;

public class AfiliadoBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    @Expose
    private String apellido1 = "";
    @Expose
    private String apellido2 = "";
    @Expose
    private String fnac= "";
    @Expose
    private String falta = "";
    @Expose
    private Integer movil = 0;
    @Expose
    private String email = "";
    
    @Expose(serialize = false)
    private Integer id_empresa = 0;
    @Expose(deserialize = false)
    private EmpresaBean obj_empresa = null;
    
    @Expose(serialize = false)
    private Integer id_centro = 0;
    @Expose(deserialize = false)
    private CentroBean obj_centro = null;
    
    @Expose(serialize = false)
    private Integer id_cuota = 0;
    @Expose(deserialize = false)
    private CuotaBean obj_cuota = null;
    
    

    public AfiliadoBean() {
        this.id = 0;
    }

    public AfiliadoBean(Integer id) {
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    public String getFalta() {
        return falta;
    }

    public void setFalta(String falta) {
        this.falta = falta;
    }

    public Integer getMovil() {
        return movil;
    }

    public void setMovil(Integer movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getId_centro() {
        return id_centro;
    }

    public void setId_centro(Integer id_centro) {
        this.id_centro = id_centro;
    }

    public CentroBean getObj_centro() {
        return obj_centro;
    }

    public void setObj_centro(CentroBean obj_centro) {
        this.obj_centro = obj_centro;
    }

    public Integer getId_cuota() {
        return id_cuota;
    }

    public void setId_cuota(Integer id_cuota) {
        this.id_cuota = id_cuota;
    }

    public CuotaBean getObj_cuota() {
        return obj_cuota;
    }

    public void setObj_cuota(CuotaBean obj_cuota) {
        this.obj_cuota = obj_cuota;
    }

   


    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "apellido1:" + apellido1 + ",";
        strJson += "apellido2:" + apellido2 + ",";
        strJson += "fnac:" + fnac + ",";
        strJson += "falta:" + falta + ",";
        strJson += "movil:" + movil + ",";
        strJson += "email:" + email + ",";
        if (expand) {
            strJson += "obj_empresa:" + obj_empresa.toJson(false) + ",";

        } else {
            strJson += "id_empresa:" + id_empresa + ",";

        }
        
        if (expand) {
            strJson += "obj_centro:" + obj_centro.toJson(false) + ",";

        } else {
            strJson += "id_centro:" + id_centro + ",";

        }
        
        if (expand) {
            strJson += "obj_cuota:" + obj_cuota.toJson(false) + ",";

        } else {
            strJson += "id_cuota:" + id_cuota + ",";

        }
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "apellido1,";
        strColumns += "apellido2,";
        strColumns += "fnac,";
        strColumns += "falta,";
        strColumns += "movil,";
        strColumns += "email,";   
        strColumns += "id_empresa,";
        strColumns += "id_centro,";
        strColumns += "id_cuota";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += nombre + ",";
        strColumns += apellido1 + ",";      
        strColumns += apellido2 + ",";
        strColumns += fnac + ",";
        strColumns += falta+ ",";      
        strColumns += movil + ",";      
        strColumns += email + ",";
        strColumns += id_empresa + ",";
        strColumns += id_centro + ",";
        strColumns += id_cuota;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "nombre=" + nombre + ",";
        strPairs += "apellido1=" + apellido1+ ",";
        strPairs += "apellido2=" + apellido2 + ",";
        strPairs += "fnac=" + fnac + ",";
        strPairs += "falta=" + falta+ ",";
        strPairs += "movil=" + movil + ",";
        strPairs += "email=" + email + ",";
        strPairs += "id_empresa=" + id_empresa + ",";
        strPairs += "id_centro=" + id_centro + ",";
        strPairs += "id_cuota=" + id_cuota;
        return strPairs;
    }

    @Override
    public AfiliadoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setApellido1(oResultSet.getString("apellido1"));
        this.setApellido2(oResultSet.getString("apellido2"));
        this.setFnac(oResultSet.getString("fnac"));
        this.setFalta(oResultSet.getString("falta"));
        this.setMovil(oResultSet.getInt("movil"));
        this.setEmail(oResultSet.getString("email"));
        
        
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
            CentroBean oCentroBean = new CentroBean();
            CentroDao oCentroDao = new CentroDao(pooledConnection);
            oCentroBean.setId(oResultSet.getInt("id_centro"));
            oCentroBean = oCentroDao.get(oCentroBean, expand - 1);
            this.setObj_centro(oCentroBean);
        } else {
            this.setId_centro(oResultSet.getInt("id_centro"));
        }
         
         if (expand > 0) {
            CuotaBean oCuotaBean = new CuotaBean();
            CuotaDao oCuotaDao = new CuotaDao(pooledConnection);
            oCuotaBean.setId(oResultSet.getInt("id_cuota"));
            oCuotaBean = oCuotaDao.get(oCuotaBean, expand - 1);
            this.setObj_cuota(oCuotaBean);
        } else {
            this.setId_cuota(oResultSet.getInt("id_cuota"));
        }
        return this;

    }

}
