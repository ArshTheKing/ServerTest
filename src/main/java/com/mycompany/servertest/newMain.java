/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.servertest;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author Azael
 */
public class newMain {
    private static final String myServiceName = "MyBtService";
    // Bluetooth service UUID
    private static final String myServiceUUID = "7f49f6fa-12e5-11ec-82a8-0242ac130003";
    private UUID MYSERVICEUUID_UUID = new UUID(myServiceUUID, false);
    private UUID[] uuids = {MYSERVICEUUID_UUID};
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final JDialog loading = new JDialog(new JFrame());
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Por favor, connecte con la aplicación BluetoothLock"));
        p1.add(new JLabel("CRECENT ROSE"));
        //loading.setUndecorated(true);
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(p1);     
        box.add(Box.createVerticalGlue());
        
        loading.getContentPane().add(box);
        //loading.setSize(200,80);
        loading.pack();
        loading.setLocationRelativeTo(new JFrame());
        loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        loading.setModal(true);
        
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws InterruptedException {
                Thread.sleep(5000);
                System.exit(0);
                return null;
            }
            @Override
            protected void done() {
                loading.dispose();
            }
        };
        worker.execute(); //here the process thread initiates
        loading.setVisible(true);
        try {
            worker.get(); //here the parent thread waits for completion
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        /* JDialog linking = new JDialog(new JFrame());
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(new JLabel("Por favor, connecte con la aplicación BluetoothLock"));
        p1.add(new JLabel("CRECENT ROSE"),BorderLayout.SOUTH);
        linking.getContentPane().add(p1);
        linking.pack();
        linking.setLocationRelativeTo(new JFrame());
        linking.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        linking.setModal(true);
        linking.setVisible(true);
            // TODO code application logic here
           /* Thread th= new Thread(){
            @Override
            public void run() {
            try {
            btServer();
            System.out.println("FIN");
            // do something in a new thread if 'called' by super.start()
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
            }
            };
            th.start();
            /*
            */
            //LockScreen lockScreen = new LockScreen();
            /*new LockScreen();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException ex) {
            System.exit(0);
        }
            System.exit(0);
        */
    }
    
    private static void btServer() throws BluetoothStateException, IOException {
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println(localDevice.getBluetoothAddress());
        localDevice.setDiscoverable(DiscoveryAgent.GIAC);
        DiscoveryAgent discoveryAgent = localDevice.getDiscoveryAgent();
        StreamConnectionNotifier streamConnectionNotifier;
        // Create notifier (and service record)
        String connURL = "btspp://localhost:"+"7f49f6fa12e511ec82a80242ac130003"+
                ";name="+myServiceName;
        streamConnectionNotifier = (StreamConnectionNotifier) Connector.open(connURL);
        
        while(true){
        StreamConnection sc = streamConnectionNotifier.acceptAndOpen();
        RemoteDevice rd = RemoteDevice.getRemoteDevice(sc);
        InputStream stream = sc.openInputStream();
            /*byte[] bytes = (new String()).getBytes("OK");
            OutputStream openOutputStream = sc.openOutputStream();
            openOutputStream.write(bytes);
            openOutputStream.close();
            */
                System.out.println(rd.getFriendlyName(true));
                System.out.println(rd.toString());
            while(true){
                byte[] b= new byte[1024];
                byte[] empty= new byte[1024];
                //int read = stream.read(b);
                int read = stream.read(b, 0, 4);
                System.out.println(read);
                if(read==-1)break;// System.out.println("HEY");
                if(new String(b).contains("exit")){
                    System.out.println("Disconected");
                }
                System.out.println(new String(b));
        }
        }
    }
}