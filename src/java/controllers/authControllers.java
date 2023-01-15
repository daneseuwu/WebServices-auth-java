/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import config.connectionsql;
import interfaces.authInterface;
import models.auth;

/**
 *
 * @author duga17
 */
public class authControllers implements authInterface {

    Connection cn;
    CallableStatement auth;
    ResultSet rs;
    String pattern = "Pr0gr4mm1ng";

    @Override
    public auth authUser(String username, String password) {
        auth user = new auth();

        try {
            cn = connectionsql.getInstancia().getConexion();
            auth = cn.prepareCall("{call sp_auth(?,?,?)}");
            auth.setString(1, username);
            auth.setString(2, password);
            auth.setString(3, pattern);
            rs = auth.executeQuery();

            if (rs.next()) {
                if (rs.getInt("id_user") > 0) {

                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));

//                    if (rs.getBoolean("Estado")) {
//                        user.setStatus(1);
//                    } else {
//                        user.setStatus(0);
//                    }

                } else {
                    user.setId_user(-1);
                    user.setError("user not found");
                }
            } else {
                user.setId_user(-1);
                user.setError("user or pass incorrect");
            }
        } catch (Exception ex) {
            user.setId_user(-1);
            user.setError("error validation" + ex.getMessage());
        }
        return user;
    }

}
