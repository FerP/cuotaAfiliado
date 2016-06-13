package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.ReciboAfiliadoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class ReciboAfiliadoDao implements ViewDaoInterface<ReciboAfiliadoBean>, TableDaoInterface<ReciboAfiliadoBean> {

    //private String strSQL = "SELECT p.id_afiliado, p.id_cuota, FROM pagoafiliado as p, afiliado as a, cuota as c WHERE p.id_afiliado=a.id and p.id_cuota=c.id and 1=1";
    private String strSQL = "Select id_recibo,id_afiliado From pagoafiliado where 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;
    private String strTable = "pagoAfiliado";

    public ReciboAfiliadoDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    // OPERACIONES BÁSICAS
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
    public ArrayList<ReciboAfiliadoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<ReciboAfiliadoBean> arrReciboAfiliadoBean = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    ReciboAfiliadoBean oReciboAfiliadoBean = new ReciboAfiliadoBean();
                    arrReciboAfiliadoBean.add(oReciboAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrReciboAfiliadoBean;
    }

    @Override
    public ArrayList<ReciboAfiliadoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<ReciboAfiliadoBean> arrReciboAfiliado = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    ReciboAfiliadoBean oReciboAfiliadoBean = new ReciboAfiliadoBean();
                    arrReciboAfiliado.add(oReciboAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrReciboAfiliado;
    }

        // -----------------
    // MÉTODO PARA SACAR EL PAGO EN LA PANTALLA DE AFILIADO
    public ReciboAfiliadoBean getAfiliadosFiltradoRecibo(ReciboAfiliadoBean oReciboAfiliadoBean, Integer expand) throws Exception {
        ResultSet oResultSet = null;
        try {

            oResultSet = oMysql.getAllSql(strSQL + "AND id_recibo=" + oReciboAfiliadoBean.getId_recibo());

            if (oResultSet != null) {
                while (oResultSet.next()) {
                    oReciboAfiliadoBean = oReciboAfiliadoBean.fill(oResultSet, oConnection, expand);
                }
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
        }

        return oReciboAfiliadoBean;
    }    

    public int getPagesReciboAfiliado(int id_recibo, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND id_recibo=" + id_recibo;
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    public int getCountReciboAfiliado(int id_recibo, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND id_recibo=" + id_recibo;
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    public ArrayList<ReciboAfiliadoBean> getPageReciboAfiliado(int id_recibo, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += "AND id_recibo=" + id_recibo;
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<ReciboAfiliadoBean> arrReciboAfiliadoBean = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    ReciboAfiliadoBean oReciboAfiliadoBean = new ReciboAfiliadoBean();
                    arrReciboAfiliadoBean.add(oReciboAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrReciboAfiliadoBean;
    }
    
    @Override
    public Integer set(ReciboAfiliadoBean oReciboAfiliadoBean) throws Exception {
          Integer iResult = null;
        try {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oReciboAfiliadoBean.getColumns() + ")";
                strSQL += "VALUES(" + oReciboAfiliadoBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);


        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }
    
    @Override
    public ReciboAfiliadoBean get(ReciboAfiliadoBean oBean, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
