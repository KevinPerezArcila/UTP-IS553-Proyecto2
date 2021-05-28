/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Conexion.Conexion;
import Interfaz.Interfaz;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class SistemaCajero {
    public static double saldoActual;
   public static final String url="jdbc:mysql://localhost:3306/bdbaniscutp";
        public static final String user="root";
        public static final String contraseña="";
        
        PreparedStatement ps ;
        ResultSet rs;
       
       
        
        public  static Connection getConnection(){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,user,contraseña);
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    
    
    
    public SistemaCajero(){
        //saldoActual=0;
        
    }
    
    public void depositarAlCajero(Integer id, Integer billete50 ,Integer billete20,Integer billete10,Integer billete5,Integer billete2){
        Connection con=null;
        Integer totalBillete50;
        Integer totalBillete20;
        Integer totalBillete10;
        Integer totalBillete5;
        Integer totalBillete2;
        Integer total;
        Integer ultimoNumero;
        
        try{
            con = getConnection();
            Statement st=con.createStatement();
            
            String validacion = "SELECT * FROM registrocajero WHERE id='"+id+"'";
            rs = st.executeQuery(validacion);
            if(rs.next()){
                totalBillete50=rs.getInt("billete50");
                totalBillete50 += billete50;
                totalBillete20=rs.getInt("billete20");
                totalBillete20 += billete20;
                totalBillete10=rs.getInt("billete10");
                totalBillete10 += billete10;
                totalBillete5=rs.getInt("billete5");
                totalBillete5 += billete5;
                totalBillete2=rs.getInt("billete2");
                totalBillete2 += billete2;
                total=(totalBillete50*50000)+(totalBillete20*20000)+(totalBillete10*10000)+(totalBillete5*5000)+(totalBillete2*2000);
                actualizarMontoCajero( totalBillete50,totalBillete20,totalBillete10,totalBillete5,totalBillete2,total,id);
                JOptionPane.showMessageDialog(null, "Cajero cargado con exito. ");
            }
            else{
                
            }
        }catch(Exception e){
            
        }
    }
    public Integer validarBilletes(Integer id, double retiro){
        Connection con = null;
        Integer totalBillete50;
        Integer totalBillete20;
        Integer totalBillete10;
        Integer totalBillete5;
        Integer totalBillete2;
        Integer totalBillete50Aux;
        Integer totalBillete20Aux;
        Integer totalBillete10Aux;
        Integer totalBillete5Aux;
        Integer totalBillete2Aux;
        Integer total;
        Integer suma=0;
        Integer billete=0;
        double contador;
        double contadorAux;
        
        try{
            con = getConnection();
            Statement st=con.createStatement();
            
            String validacion = "SELECT * FROM registrocajero WHERE id='"+id+"'";
            rs = st.executeQuery(validacion);
            if(rs.next()){
                totalBillete50=rs.getInt("billete50");
                
                totalBillete20=rs.getInt("billete20");
                
                totalBillete10=rs.getInt("billete10");
               
                totalBillete5=rs.getInt("billete5");
                
                totalBillete2=rs.getInt("billete2");
                
                total =rs.getInt("total");
                
                
                contador=retiro;
                for(int i = 0; i<totalBillete50; i++ ){
                    if(suma<retiro){
                        if(contador>=50000){
                            suma+=50000;
                            billete = i+1;
                            contador=contador-50000;
                        }
                        
                    }
                    
                }
                totalBillete50Aux=billete;
                totalBillete50=totalBillete50-billete;
                billete=0;
                for(int i = 0; i<totalBillete20; i++ ){
                    if(suma<retiro){
                        if(contador>=20000){
                            suma+=20000;
                            billete = i+1;
                            contador=contador-20000;
                        }
                        
                    }
                    
                }
                totalBillete20Aux=billete;
               totalBillete20=totalBillete20-billete;
               billete=0;
               for(int i = 0; i<totalBillete10; i++ ){
                    if(suma<retiro){
                        if(contador>=10000){
                        
                            suma+=10000;
                            billete = i+1;
                            contador=contador-10000;
                        }
                        
                    }
                    
                }
               totalBillete10Aux=billete;
               totalBillete10=totalBillete10-billete;
               billete=0;
               
               for(int i = 0; i<totalBillete5; i++ ){
                    if(suma<retiro){
                        
                        if(contador>=5000){
                            
                            if(contador<10000){
                                 contadorAux=contador-5000;
                                if((contadorAux%2000)==0){
                                 suma+=5000;
                                 billete = i+1;
                                 contador=contador-5000;
                            }
                                
                            }
                            else{
                                suma+=5000;
                                billete=i+1;
                                contador=contador-5000;
                            }
                           
                            
               
                        }
                      
                        
                    }
                    
                }
               totalBillete5Aux=billete;
               totalBillete5=totalBillete5-billete;
               billete=0;
               for(int i = 0; i<totalBillete2; i++ ){
                     
                    if(suma<retiro){
                        if(contador>=2000){
                            suma+=2000;
                            billete = i+1;
                            contador=contador-2000;
                        }
                        
                        
                    }
                    
                }
               totalBillete2Aux=billete;
               totalBillete2=totalBillete2-billete;
               billete=0;
               
               if(suma==retiro){
                   
                   //total=(totalBillete50*50000)+(totalBillete20*20000)+(totalBillete10*10000)+(totalBillete5*5000)+(totalBillete2*2000);
                   //actualizarMontoCajero( totalBillete50,totalBillete20,totalBillete10,totalBillete5,totalBillete2,total,id);
                   
                   //JOptionPane.showMessageDialog(null, "Se entregaron "+totalBillete50Aux+" de 50mil, "+totalBillete20Aux+" de 20mil, "+totalBillete10Aux+" de 10 mil, "+totalBillete5Aux+" de 5mil y "+totalBillete2Aux+" de 2mil. ");
                   return 1;
               
               }
                
               else{
                   
                   return 0;
               }
               
               
               
            }
            
            else{
                
            }
        }catch(Exception e){
            
        }

    return 0;
    }
    
    public String validarBilletes2(Integer id, double retiro){
        Connection con = null;
        Integer totalBillete50;
        Integer totalBillete20;
        Integer totalBillete10;
        Integer totalBillete5;
        Integer totalBillete2;
        Integer totalBillete50Aux;
        Integer totalBillete20Aux;
        Integer totalBillete10Aux;
        Integer totalBillete5Aux;
        Integer totalBillete2Aux;
        Integer total;
        Integer suma=0;
        Integer billete=0;
        double contador;
        double contadorAux;
        
        try{
            con = getConnection();
            Statement st=con.createStatement();
            
            String validacion = "SELECT * FROM registrocajero WHERE id='"+id+"'";
            rs = st.executeQuery(validacion);
            if(rs.next()){
                totalBillete50=rs.getInt("billete50");
                
                totalBillete20=rs.getInt("billete20");
                
                totalBillete10=rs.getInt("billete10");
               
                totalBillete5=rs.getInt("billete5");
                
                totalBillete2=rs.getInt("billete2");
                
                total =rs.getInt("total");
                
                
                contador=retiro;
                for(int i = 0; i<totalBillete50; i++ ){
                    if(suma<retiro){
                        if(contador>=50000){
                            suma+=50000;
                            billete = i+1;
                            contador=contador-50000;
                        }
                        
                    }
                    
                }
                totalBillete50Aux=billete;
                totalBillete50=totalBillete50-billete;
                billete=0;
                for(int i = 0; i<totalBillete20; i++ ){
                    if(suma<retiro){
                        if(contador>=20000){
                            suma+=20000;
                            billete = i+1;
                            contador=contador-20000;
                        }
                        
                    }
                    
                }
                totalBillete20Aux=billete;
               totalBillete20=totalBillete20-billete;
               billete=0;
               for(int i = 0; i<totalBillete10; i++ ){
                    if(suma<retiro){
                        if(contador>=10000){
                        
                            suma+=10000;
                            billete = i+1;
                            contador=contador-10000;
                        }
                        
                    }
                    
                }
               totalBillete10Aux=billete;
               totalBillete10=totalBillete10-billete;
               billete=0;
               
               for(int i = 0; i<totalBillete5; i++ ){
                    if(suma<retiro){
                        
                        if(contador>=5000){
                            
                            if(contador<10000){
                                 contadorAux=contador-5000;
                                if((contadorAux%2000)==0){
                                 suma+=5000;
                                 billete = i+1;
                                 contador=contador-5000;
                            }
                                
                            }
                            else{
                                suma+=5000;
                                billete=i+1;
                                contador=contador-5000;
                            }
                           
                            
               
                        }
                      
                        
                    }
                    
                }
               totalBillete5Aux=billete;
               totalBillete5=totalBillete5-billete;
               billete=0;
               for(int i = 0; i<totalBillete2; i++ ){
                     
                    if(suma<retiro){
                        if(contador>=2000){
                            suma+=2000;
                            billete = i+1;
                            contador=contador-2000;
                        }
                        
                        
                    }
                    
                }
               totalBillete2Aux=billete;
               totalBillete2=totalBillete2-billete;
               billete=0;
               
               if(suma==retiro){
                   
                   total=(totalBillete50*50000)+(totalBillete20*20000)+(totalBillete10*10000)+(totalBillete5*5000)+(totalBillete2*2000);
                   actualizarMontoCajero( totalBillete50,totalBillete20,totalBillete10,totalBillete5,totalBillete2,total,id);
                   
                   return "Se entregaron "+totalBillete50Aux+" de 50mil, "+totalBillete20Aux+" de 20mil, "+totalBillete10Aux+" de 10 mil, "+totalBillete5Aux+" de 5mil y "+totalBillete2Aux+" de 2mil. ";
                   
               
               }
                
               else{
                   
                   
               }
               
               
               
            }
            
            else{
                
            }
        }catch(Exception e){
            
        }

    return "a";
    }
    
    public void actualizarMontoCajero(Integer totalBillete50,Integer totalBillete20, Integer totalBillete10,Integer totalBillete5,Integer totalBillete2,Integer total,Integer id){
        Connection con = null;
        String actualizacion = "UPDATE registrocajero SET billete50=?, billete20=?,billete10=?, billete5=?, billete2=?, total=? where id='"+id+"'";
        try {
            con = getConnection();
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(actualizacion);
            pst.setInt(1, totalBillete50);
            pst.setInt(2, totalBillete20);
            pst.setInt(3, totalBillete10);
            pst.setInt(4, totalBillete5);
            pst.setInt(5, totalBillete2);
            pst.setInt(6, total);
            Integer n=pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void depositar(double deposito, String codigoBd, String contraseñaBd){
        Connection con=null;
        try{
            con = getConnection();
                Statement st=con.createStatement();
                String validacion="SELECT * FROM registros WHERE codigo='"+codigoBd+"' and contraseña='"+contraseñaBd+"' ";
                
                rs = st.executeQuery(validacion);
                if(rs.next()){
                    saldoActual=rs.getDouble("saldo");
                    saldoActual += deposito;
                    actualizarDatos(saldoActual, codigoBd);
                    JOptionPane.showMessageDialog(null, "Cuenta cargada con exito. ");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar esta operacion porque su usuario u contraseña es incorrecta. ");
                }
            
        }catch(Exception e){
            
        }
        
    }
    public double totalCajero(Integer id){
        double total=0;
        Connection con=null;
         try{
            con = getConnection();
                Statement st=con.createStatement();
                String validacion="SELECT * FROM registrocajero WHERE id=' "+id+"' ";
                
                rs = st.executeQuery(validacion);
                if(rs.next()){
                    total=rs.getDouble("total");
                    
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar esta operacion porque su usuario u contraseña es incorrecta. ");
                }
            
        }catch(Exception e){
            
        }
        
        
        return total;
    }
    
    public String retirar (double retiro, String codigoBd, String contraseñaBd ){
        Connection con=null;
        try{
            con = getConnection();
                Statement st=con.createStatement();
                String validacion="SELECT * FROM registros WHERE codigo='"+codigoBd+"' and contraseña='"+contraseñaBd+"' ";
                
                rs = st.executeQuery(validacion);
                
                if(rs.next()){
                    saldoActual=rs.getDouble("saldo");
                    if(saldoActual>=retiro ){
                    saldoActual-=retiro;
                    actualizarDatos(saldoActual, codigoBd);
                    return "Retiro del dinero exitoso";
                
                    //JOptionPane.showMessageDialog(null, "Retiro del dinero exitoso. ");
                 }
                    
        else{
            
           // JOptionPane.showMessageDialog(null, "Saldo insuficiente, consulte su saldo. ");
            saldoActual = -1;
           return "Saldo insuficiente, consulte su saldo.";
            
        }
                    
                }
                else{
                    
                    //JOptionPane.showMessageDialog(null, "No se pudo realizar esta operacion porque su usuario u contraseña es incorrecta. ");
                    saldoActual=0;
                    return "No se pudo realizar esta operacion porque su usuario u contraseña es incorrecta. ";
                  
                    
                }
            
        }catch(Exception e){
            
        }
        return "a";
    }
    
    public Integer validarCuenta (double retiro, String codigoBd, String contraseñaBd ){
        Connection con=null;
        Integer confirmacion=0;
        double saldo;
        try{
            con = getConnection();
                Statement st=con.createStatement();
                String validacion="SELECT * FROM registros WHERE codigo='"+codigoBd+"' and contraseña='"+contraseñaBd+"' ";
                
                rs = st.executeQuery(validacion);
                
                if(rs.next()){
                    saldo=rs.getDouble("saldo");
                    if(saldo>=retiro ){
                   
                    
                    
                    confirmacion=1;
                 }
                    
        else{
            //JOptionPane.showMessageDialog(null, "Saldo insuficiente, consulte su saldo. ");
            confirmacion=2;
        }
                    
                }
                else{
                    //JOptionPane.showMessageDialog(null, "No se pudo realizar esta operacion porque su usuario u contraseña es incorrecta. ");
                    confirmacion=3;
                }
            
        }catch(Exception e){
            
        }
        return confirmacion;
    }
    
    public Integer contador(){
            Integer numero=0;
            Connection con=null;
            try{
            con = getConnection();
                Statement st=con.createStatement();
                String validacion="SELECT * FROM contadorcajero WHERE id=1";
                
                rs = st.executeQuery(validacion);
                if(rs.next()){
                    numero=rs.getInt("contador");
                    
                }
                
            
        }catch(Exception e){
            
        }   
            actualizarContador((numero+1));
            
      
        
           
            return numero;
    }
    public void actualizarContador(Integer numero){
        Connection con=null;
        String actualizacion="UPDATE contadorcajero set contador=? where id=1";
            try{
                con = getConnection();
                PreparedStatement pst=(PreparedStatement) con.prepareStatement(actualizacion);
                pst.setInt(1, numero);
                Integer n=pst.executeUpdate();

            }catch(Exception e){
            
        }
    }
    
    
    
    public void actualizarDatos(double saldoActual,String codigoBd){
        Connection con=null;
        
        String actualizacion="UPDATE registros set saldo=? where codigo='"+codigoBd+"'";
        try{
            con = getConnection();
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(actualizacion);
            pst.setDouble(1, saldoActual);
            Integer n=pst.executeUpdate();
            
        }catch(Exception e){
            
        }
        
    }
    
    public double obtenerSaldo(){
       
        return saldoActual; 
    }
    
    public void obtenerSaldo2(String codigoBd, String contraseñaBd){
        Connection con=null;
        try{
            con = getConnection();
                Statement st=con.createStatement();
                String validacion="SELECT * FROM registros WHERE codigo='"+codigoBd+"' and contraseña='"+contraseñaBd+"' ";
                
                rs = st.executeQuery(validacion);
                if(rs.next()){
                    saldoActual=rs.getDouble("saldo");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar esta operacion porque su usuario u contraseña es incorrecta. ");
                    saldoActual=0;
                }
            
        }catch(Exception e){
            
        }
        
        
    }
    
    
}
