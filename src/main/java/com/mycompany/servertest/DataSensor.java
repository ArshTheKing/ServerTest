/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servertest;

/*import actuator.Actuator;
import actuator.NetworkShutdownActuator;
import actuator.ShutdownActuator;
import actuator.UserBlockActuator;
*/import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.bluetooth.BluetoothStateException;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author AZAEL
 */
public class DataSensor extends Thread{
    private static  DataSensor myself;
    //private Actuator action;
    private boolean exit;
    private InputStream deviceStream;

    private DataSensor(InputStream stream, int actuator) {
        super();
        this.deviceStream = stream;
        setAction(actuator);
    }
    public static DataSensor getInstance(){
        return (myself==null) ? myself=new DataSensor(null,0): myself;
    }

    private void setAction(int actuator) {
        /*switch(actuator){
            case 0:this.action=new NetworkShutdownActuator();
             break;
            case 1:this.action=new ShutdownActuator();
             break;
            case 2:this.action=new UserBlockActuator();
             break;
        }*/
    }
    
    public void setKey(Stream stream) {
        this.deviceStream = (InputStream) stream;
    }

    
    public void setMode(int mode) {
        setAction(mode);
    }
    

    public void run() {
        try {
            while(exit ){
                byte[] b= new byte[1024];
                int read = deviceStream.read(b,0,4);
                if(read==-1) System.out.println("Device dc");
                System.out.println(new String(b)); 
                //Control.updateUIBatLvl(new String(b));
            }
        } catch (IOException ex) {
            Logger.getLogger(DataSensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sleep() {
        this.suspend();
    }

    public void begin(InputStream stream) {
        Thread.State state=getState();
        if(state.equals(Thread.State.NEW)) this.start();
        else {
            this.deviceStream=stream;
            this.resume();
        }
    }


    
    
}
