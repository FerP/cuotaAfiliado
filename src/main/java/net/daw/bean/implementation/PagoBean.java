package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.AfiliadoDao;
import net.daw.dao.implementation.CuotaDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.dao.implementation.PeriodoDao;



public class PagoBean implements GenericBean {

    @Expose
    private Integer id;
    
    @Expose
    private String fpago = "";
   

    @Expose(serialize = false)
    private Integer id_afiliado = 0;
    @Expose(deserialize = false)
    private AfiliadoBean obj_afiliado = null;
    
    @Expose(serialize = false)
    private Integer id_cuota = 0;
    @Expose(deserialize = false)
    private CuotaBean obj_cuota = null;
    
    @Expose(serialize = false)
    private Integer id_periodo = 0;
    @Expose(deserialize = false)
    private PeriodoBean obj_periodo = null;

    public PagoBean() {
        this.id = 0;
    }

    public PagoBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFpago() {
        return fpago;
    }

    public void setFpago(String fpago) {
        this.fpago = fpago;
    }

    public Integer getId_afiliado() {
        return id_afiliado;
    }

    public void setId_afiliado(Integer id_afiliado) {
        this.id_afiliado = id_afiliado;
    }

    public AfiliadoBean getObj_afiliado() {
        return obj_afiliado;
    }

    public void setObj_afiliado(AfiliadoBean obj_afiliado) {
        this.obj_afiliado = obj_afiliado;
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

    public Integer getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(Integer id_periodo) {
        this.id_periodo = id_periodo;
    }

    public PeriodoBean getObj_periodo() {
        return obj_periodo;
    }

    public void setObj_periodo(PeriodoBean obj_periodo) {
        this.obj_periodo = obj_periodo;
    }

  

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "fpago:" + fpago + ",";
     

        if (expand) {
            strJson += "obj_afiliado:" + obj_afiliado.toJson(false) + ",";

        } else {
            strJson += "id_afiliado:" + id_afiliado + ",";

        }
        
         if (expand) {
            strJson += "obj_cuota:" + obj_cuota.toJson(false) + ",";

        } else {
            strJson += "id_cuota:" + id_cuota + ",";

        }
         
          if (expand) {
            strJson += "obj_periodo:" + obj_periodo.toJson(false) + ",";

        } else {
            strJson += "id_periodo:" + id_periodo + ",";

        }
        
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";      
        strColumns += "fpago,";
        strColumns += "id_afiliado,";
        strColumns += "id_cuota,";
        strColumns += "id_periodo";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ","; 
        strColumns += fpago+ ",";
        strColumns += id_afiliado + ",";
        strColumns += id_cuota + ",";      
        strColumns += id_periodo;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";     
        strPairs += "fpago=" + fpago + ",";
        strPairs += "cif=" + id_afiliado+ ",";
        strPairs += "contacto=" + id_cuota + ",";      
        strPairs += "id_usuario=" + id_periodo;

        return strPairs;
    }

    @Override
     public PagoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFpago(oResultSet.getString("fpago"));
       
       if (expand > 0) {
            AfiliadoBean oAfiliadoBean = new AfiliadoBean();
            AfiliadoDao oAfiliadoDao = new AfiliadoDao(pooledConnection);
            oAfiliadoBean.setId(oResultSet.getInt("id_afiliado"));
            oAfiliadoBean = oAfiliadoDao.get(oAfiliadoBean, expand - 1);
            this.setObj_afiliado(oAfiliadoBean);
        } else {
            this.setId_afiliado(oResultSet.getInt("id_afiliado"));
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
       
       
       if (expand > 0) {
            PeriodoBean oPeriodoBean = new PeriodoBean();
            PeriodoDao oPeriodoDao = new PeriodoDao(pooledConnection);
            oPeriodoBean.setId(oResultSet.getInt("id_periodo"));
            oPeriodoBean = oPeriodoDao.get(oPeriodoBean, expand - 1);
            this.setObj_periodo(oPeriodoBean);
        } else {
            this.setId_periodo(oResultSet.getInt("id_periodo"));
        }
       
        return this;
    }

    
}
