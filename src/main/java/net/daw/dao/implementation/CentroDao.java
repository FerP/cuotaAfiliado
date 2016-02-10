
package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import net.daw.bean.implementation.CentroBean;

import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;

import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class CentroDao implements ViewDaoInterface<CentroBean>, TableDaoInterface<CentroBean> {

    private String strTable = "centro";
//private String strSQL = "SELECT * FROM centro, municipio, sector WHERE sector.id=centro.id_sector and municipio.id=centro.id_municipio and 1=1";

//private String strSQL = "SELECT c.id, c.nombre, c.direccion, c.cp, c.telefono, e.nombre, c.id_empresa, m.nombre, c.id_municipio, c.id_sector, s.nombre FROM centro c, empresa e, municipio m, sector s WHERE m.id=c.id_municipio AND s.id=c.id_sector AND e.id=c.id_empresa AND 1=1 ";
private String strSQL = "SELECT * FROM centro WHERE 1=1";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

//   
    
    public CentroDao(Connection oPooledConnection) throws Exception {
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
    public ArrayList<CentroBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<CentroBean> arrCentro = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    CentroBean oCentroBean = new CentroBean();
                    arrCentro.add(oCentroBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrCentro;
    }

    @Override
    public ArrayList<CentroBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<CentroBean> arrCentro = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    CentroBean oCentroBean = new CentroBean();
                    arrCentro.add(oCentroBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrCentro;
    }

    @Override
    public CentroBean get(CentroBean oCentroBean, Integer expand) throws Exception {
        if (oCentroBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oCentroBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oCentroBean = oCentroBean.fill(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oCentroBean.setId(0);
        }
        return oCentroBean;
    }

    @Override
    public Integer set(CentroBean oCentroBean) throws Exception {
          Integer iResult = null;
        try {
            if (oCentroBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oCentroBean.getColumns() + ")";
                strSQL += "VALUES(" + oCentroBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oCentroBean.toPairs();
                strSQL += " WHERE id=" + oCentroBean.getId();
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
