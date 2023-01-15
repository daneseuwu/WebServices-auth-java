/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import models.auth;

/**
 *
 * @author duga17
 */
public interface authInterface {

    public auth authUser(
            String username,
            String password);
}
