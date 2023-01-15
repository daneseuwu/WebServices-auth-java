/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServiceSOAP;

import controllers.authControllers;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import models.auth;

/**
 *
 * @author mdeodanes
 */
@WebService(serviceName = "WebServiceSOAP")
public class WebServiceSOAP {

    /**
     * This is a sample web service operation
     */
    authControllers dauth = new authControllers();

    @WebMethod(operationName = "authUser")
    public auth authUser(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    ) {
        auth user = new auth();
        user = dauth.authUser(username, password);
        return user;
    }
}
