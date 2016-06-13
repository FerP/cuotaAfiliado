package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class LiquidacionDao {

    private String strSQL = "SELECT SUM(cuota.importe) as total FROM pago INNER JOIN cuota ON pago.id_cuota = cuota.id WHERE 1 = 1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public LiquidacionDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    public String getLiquidacionTotal() throws Exception {
        String resultado = null;
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    Double total = oResultSet.getDouble("total");
                    resultado = "{\"total\":" + total + "}";
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
        }

        return resultado;
    }
    
    public String getLiquidacionPorCuota(Integer idCuota) throws Exception {
        String resultado = null;
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL  + " And pago.id_cuota= " + idCuota + " ");
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    Double total = oResultSet.getDouble("total");
                    resultado = "{\"total\":" + total + "}";
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
        }

        return resultado;
    }

}
