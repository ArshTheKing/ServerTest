/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servertest;

/**
 *
 * @author Azael
 */
public class ConnectionSensor extends Thread{
    
    private static ConnectionSensor myself=null;
    
    
    public static ConnectionSensor getInstance(){
        return (myself==null)? new ConnectionSensor(): myself;
    }

    private ConnectionSensor() {
        myself=this;
    }
    
    

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
}
