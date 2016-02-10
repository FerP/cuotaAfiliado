package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.PagoAfiliadoBean;
import net.daw.bean.implementation.PagoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

    public class PagoAfiliadoDao implements ViewDaoInterface<PagoAfiliadoBean>, TableDaoInterface<PagoAfiliadoBean> {
   
   /* private String strSQL = "SELECT pa.id_a, pa.id_c, pa.id_r, a.nombre as nombreafiliado, a.apellido1, a.movil, c.tipocuota as tipocuotacuota, c.importe, r.descripcion as descripcionrecibo, r.emision, r.periodo FROM pagoafiliado pa, afiliado a, cuota c, recibo r WHERE a.id = pa.id_afiliado AND c.id = pa.id_cuota AND r.id= pa.id_recibo AND 1=1  ";  */
    
    private String strSQL = "select * from pagoAfiliado where 1=1";
    private String strTable = "pagoafiliado";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;
    
    
    

    public PagoAfiliadoDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    @Override
    public int getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    @Override
    public ArrayList<PagoAfiliadoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
    
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<PagoAfiliadoBean> arrPagoAfiliadoBean = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPagoAfiliadoBean.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPagoAfiliadoBean;
    }

    @Override
    public ArrayList<PagoAfiliadoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<PagoAfiliadoBean> arrPagoAfiliado = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPagoAfiliado.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPagoAfiliado;
    }

    @Override
    public PagoAfiliadoBean get(PagoAfiliadoBean oPagoAfiliadoBean, Integer expand) throws Exception {
        if (oPagoAfiliadoBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oPagoAfiliadoBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oPagoAfiliadoBean = oPagoAfiliadoBean.fill(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oPagoAfiliadoBean.setId(0);
        }
        return oPagoAfiliadoBean;
    }

    /*Filtrar pago por afiliado*/
     public PagoAfiliadoBean getPagoFiltradoPorAfiliado(PagoAfiliadoBean oPagoAfiliadoBean, Integer expand) throws Exception {
        ResultSet oResultSet = null;
        try {

            oResultSet = oMysql.getAllSql(strSQL + " AND pa.id_afiliado= " + oPagoAfiliadoBean.getId_afiliado());

            if (oResultSet != null) {
                while (oResultSet.next()) {
                    oPagoAfiliadoBean = oPagoAfiliadoBean.fill(oResultSet, oConnection, expand);
                }
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
        }

        return oPagoAfiliadoBean;
    }
    
    
    
    
    /*Métodos necesarios para obtener cuotas respectivas de cada afiliado*/
    
    
    
    /*Respecto afiliado*/
    
    public int getPagesAfiliado(int id_afiliado, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND pa.id_afiliado=" + id_afiliado;
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

     public int getCountAfiliado(int id_afiliado, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND pa.id_afiliado=" + id_afiliado;
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
    
     public ArrayList<PagoAfiliadoBean> getPageAfiliado(int id_afiliado, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += "AND pa.id_afiliado=" + id_afiliado;
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<PagoAfiliadoBean> arrPagoAfiliadoBean = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPagoAfiliadoBean.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPagoAfiliadoBean;
    }
    
    /*Respecto cuota*/
     
     
     public int getPagesCuota(int id_cuota, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND pa.id_cuota=" + id_cuota;
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    public int getCountCuota(int id_cuota, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND pa.id_cuota=" + id_cuota;
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    public ArrayList<PagoAfiliadoBean> getPageCuota(int id_cuota, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += "AND pa.id_cuota=" + id_cuota;
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<PagoAfiliadoBean> arrPagoAfiliadoBean= new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPagoAfiliadoBean.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPagoAfiliadoBean;
    }
     
     
      /*Respecto recibo*/
     
     
      
     
     public int getPagesRecibo(int id_recibo, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND pa.id_recibo=" + id_recibo;
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    public int getCountRecibo(int id_recibo, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND pa.id_recibo=" + id_recibo;
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    public ArrayList<PagoAfiliadoBean> getPageRecibo(int id_recibo, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += "AND pa.id_recibo=" + id_recibo;
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<PagoAfiliadoBean> arrPagoAfiliadoBean= new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPagoAfiliadoBean.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPagoAfiliadoBean;
    }
     
    
    /*Otros métodos*/
    @Override
    public Integer set(PagoAfiliadoBean oPagoAfiliadoBean) throws Exception {
          Integer iResult = null;
        try {
            if (oPagoAfiliadoBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oPagoAfiliadoBean.getColumns() + ")";
                strSQL += "VALUES(" + oPagoAfiliadoBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oPagoAfiliadoBean.toPairs();
                strSQL += " WHERE id=" + oPagoAfiliadoBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        }
        return result;
    }

    

}
