package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.AfiliadoDao;
import net.daw.dao.implementation.CuotaDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.dao.implementation.EmpresaDao;
import net.daw.dao.implementation.ReciboDao;
import net.daw.helper.statics.EncodingUtilHelper;



public class PagoBean implements GenericBean {

    @Expose
    private Integer id;
    
    @Expose   
    private Date fpago = new Date();
   
    @Expose(serialize = false)
    private Integer id_afiliado = 0;
    @Expose(deserialize = false)
    private AfiliadoBean obj_afiliado = null;
    
    @Expose(serialize = false)
    private Integer id_cuota = 0;
    @Expose(deserialize = false)
    private CuotaBean obj_cuota = null;
    
    @Expose(serialize = false)
    private Integer id_recibo = 0;
    @Expose(deserialize = false)
    private ReciboBean obj_recibo = null;

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

    public Date getFpago() {
        return fpago;
    }

    public void setFpago(Date fpago) {
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

    public Integer getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(Integer id_recibo) {
        this.id_recibo = id_recibo;
    }

    public ReciboBean getObj_recibo() {
        return obj_recibo;
    }

    public void setObj_recibo(ReciboBean obj_recibo) {
        this.obj_recibo = obj_recibo;
    }

  

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "fpago:" + fpago + ",";
        strJson += "id_afiliado:" + id_afiliado+ ",";
        strJson += "id_cuota:" + id_cuota + ",";
        strJson += "id_recibo:" + id_recibo;
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
        strColumns += "id_recibo";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ","; 
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fpago)+ ",";
        strColumns += id_afiliado + ",";
        strColumns += id_cuota +",";
        strColumns += id_recibo;
        

        return strColumns;
    }

    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strPairs = "";
        strPairs += "id=" + id + ",";     
        strPairs += "fpago=" + EncodingUtilHelper.stringifyAndQuotate(fpago) + ",";
        strPairs += "id_afiliado=" + id_afiliado+ ",";
        strPairs += "id_cuota=" + id_cuota + ",";      
        strPairs += "id_recibo=" + id_recibo;

        return strPairs;
    }

    @Override
     public PagoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFpago(oResultSet.getDate("fpago"));
       
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
            ReciboBean oReciboBean = new ReciboBean();
            ReciboDao oReciboDao = new ReciboDao(pooledConnection);
            oReciboBean.setId(oResultSet.getInt("id_recibo"));
            oReciboBean = oReciboDao.get(oReciboBean, expand - 1);
            this.setObj_recibo(oReciboBean);
        } else {
            this.setId_recibo(oResultSet.getInt("id_recibo"));
        }
       
        return this;
    }

    
}
