package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.helper.statics.EncodingUtilHelper;

public class UsuarioBean implements GenericBean{

    @Expose
    private Integer id;
    @Expose
    private String login = "";
    @Expose
    private String password = "";
    @Expose
    private String descripcion = "";

    public UsuarioBean() {
        this.id = 0;
    }

    public UsuarioBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "login:" + EncodingUtilHelper.quotate(login) + ",";
        strJson += "password:" + EncodingUtilHelper.quotate(password) + ",";
        strJson += "descripcion:" + EncodingUtilHelper.quotate(descripcion) + ",";
        strJson += "}";
        return strJson;
    }
    
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
         strColumns += "login,";
        strColumns += "password,";
        strColumns += "descripcion";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += EncodingUtilHelper.quotate(login)+ ",";
        strColumns += EncodingUtilHelper.quotate(password) + ",";
        strColumns += EncodingUtilHelper.quotate(descripcion) + ",";
        strColumns += descripcion;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "login=" + EncodingUtilHelper.quotate(login) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(password) + ",";
        strPairs += "descripcion=" + EncodingUtilHelper.quotate(descripcion);

        return strPairs;
    }

    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setLogin(oResultSet.getString("login"));
        this.setPassword(oResultSet.getString("password"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        return this;

    }

}
