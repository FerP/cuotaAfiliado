package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.dao.implementation.MunicipioDao;
import net.daw.dao.implementation.PeriodoDao;
import net.daw.dao.implementation.SectorDao;
import net.daw.dao.implementation.UsuarioDao;

public class LiquidacionBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String fliquidacion = "";   
    @Expose
    private Integer importe= 0;
    
    @Expose(serialize = false)
    private Integer id_periodo = 0;
    @Expose(deserialize = false)
    private PeriodoBean obj_periodo = null;
    
   
    
    

    public LiquidacionBean() {
        this.id = 0;
    }

    public LiquidacionBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFliquidacion() {
        return fliquidacion;
    }

    public void setFliquidacion(String fliquidacion) {
        this.fliquidacion = fliquidacion;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
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
        strJson += "fliquidacion:" + fliquidacion + ",";
        strJson += "importe:" + importe + ",";
        
        
        if (expand) {
            strJson += "obj_periodo:" + obj_periodo.toJson(false) + ",";

        } else {
            strJson += "id_periodo:" + id_periodo + ",";

        }
        
        
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fliquidacion,";
        strColumns += "importe,";
        strColumns += "id_periodo";

       
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += fliquidacion + ",";
        strColumns += importe+ ",";      
        strColumns += id_periodo ;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "fliquidacion=" + fliquidacion+ ",";
        strPairs += "importe=" + importe+ ",";
        strPairs += "id_periodo=" + id_periodo;
        return strPairs;
    }

    @Override
    public LiquidacionBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFliquidacion(oResultSet.getString("fliquidacion"));
        this.setImporte(oResultSet.getInt("Importe"));

        
        
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
