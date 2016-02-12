package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.PagoAfiliadoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class PagoAfiliadoDao implements ViewDaoInterface<PagoAfiliadoBean>, TableDaoInterface<PagoAfiliadoBean> {

    private String strTable = "pagoAfiliado";
    private String strSQL = "SELECT p.id, a.nombre, a.apellido1, a.apellido2, c.tipocuota FROM pagoAfiliado as p, afiliado as a, cuota as c WHERE p.id_afiliado=a.id and p.id_cuota=c.id and 1=1";
    //private String strSQL="SELECT * FROM pagoafiliado p, afiliado a,  WHERE 1=1";
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
        ArrayList<PagoAfiliadoBean> arrPago = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPago.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPago;
    }

    @Override
    public ArrayList<PagoAfiliadoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<PagoAfiliadoBean> arrPago = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PagoAfiliadoBean oPagoAfiliadoBean = new PagoAfiliadoBean();
                    arrPago.add(oPagoAfiliadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPago;
    }

        // -----------------
    // MÉTODO PARA SACAR EL PAGO EN LA PANTALLA DE AFILIADO
    public PagoAfiliadoBean getPagosFiltradoAfiliado(PagoAfiliadoBean oPagoAfiliadoBean, Integer expand) throws Exception {
        ResultSet oResultSet = null;
        try {

            oResultSet = oMysql.getAllSql(strSQL + " AND id_afiliado= " + oPagoAfiliadoBean.getId_afiliado());

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
    
    @Override
    public PagoAfiliadoBean get(PagoAfiliadoBean oPagoAfiliadoBean, Integer expand) throws Exception {
        if (oPagoAfiliadoBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id_afiliado= " + oPagoAfiliadoBean.getId() + " ");
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
