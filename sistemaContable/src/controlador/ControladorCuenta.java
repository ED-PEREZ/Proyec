/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.Conexion;
import modelo.Cuenta;
import java.util.ArrayList;
import java.util.concurrent.locks.StampedLock;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class ControladorCuenta {

    public Conexion conexion;

    public ControladorCuenta() {
        conexion = new Conexion();
        PreparedStatement st = null;
    }

    public void AgregarCuenta(Cuenta cuentita) {
        try {
            conexion.abrirConexion();
            Statement st = conexion.abrirConexion().createStatement();
            System.out.println("ago otro");
            String sql = "INSERT INTO cuenta (codigo,nombre,tiposaldo,idorden,nivel) VALUES"
                    + " ('" + cuentita.getCodigo() + "','" + cuentita.getNombre() + "','" + cuentita.getTipoSaldo() + "'," + cuentita.getIdOrden() + "," + cuentita.getNivel() + ")";
            st.executeUpdate(sql);
//            st.executeUpdate("INSERT INTO cuenta (codigo,nombre,tiposaldo) VALUES"
//                    + "('"+cuentita.getCodigo()+",'"+cuentita.getNombre()+"','"+cuentita.getTipoSaldo()+"')");
            System.out.println("Haces esto");
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean Verificar(Integer auxi) {
        Integer idCuenta = null, codigo = null;
        try {
            conexion.abrirConexion();
            Statement st = conexion.abrirConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT idcuenta,codigo FROM cuenta WHERE codigo="
                    + "" + auxi + "");

            while (rs.next()) {
                idCuenta = rs.getInt(1);
                codigo = rs.getInt(2);
            }
            System.out.println("llego o no: " + idCuenta);
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (codigo == auxi) {
            return true;
        }
        return false;
    }

    public Integer obtenerId(Integer auxi) {
        Integer idCuenta = null, codigo = null;
        try {
            conexion.abrirConexion();
            Statement st = conexion.abrirConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT idcuenta,codigo FROM cuenta WHERE codigo="
                    + "" + auxi + "");

            while (rs.next()) {
                idCuenta = rs.getInt(1);
                codigo = rs.getInt(2);
            }
            System.out.println("llego: " + idCuenta);
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e);
        }
        return idCuenta;
    }

    public ArrayList<Cuenta> obtenerCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        ResultSet rs = null;
        try {
            Connection accesoDB = conexion.abrirConexion();
            String sql = "SELECT * FROM cuenta ORDER BY codigo ASC";
            PreparedStatement ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("idcuenta"));
                cuenta.setCodigo(rs.getString("codigo"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setTipoSaldo(rs.getString("tiposaldo"));
                cuenta.setNivel(rs.getInt("nivel"));
                cuentas.add(cuenta);
            }
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return cuentas;
    }

    public void eliminar(String codigo) {
        try {
            this.conexion.abrirConexion();
            Statement st = this.conexion.abrirConexion().createStatement();
            st.executeUpdate("DELETE FROM cuenta WHERE codigo='" + codigo+ "'");
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR " + e);
        }
    }

    public Cuenta buscarCuenta(String codigo) {
        Cuenta cuenta = new Cuenta();
        ResultSet rs = null;
        try {
            Connection accesoDB = conexion.abrirConexion();
            String sql = "SELECT * FROM cuenta WHERE codigo= '"+codigo+"'";
            PreparedStatement ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cuenta.setId(rs.getInt("idcuenta"));
                cuenta.setCodigo(rs.getString("codigo"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setTipoSaldo(rs.getString("tiposaldo"));
                cuenta.setNivel(rs.getInt("nivel"));
            }
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return cuenta;
    }

    public void ModificarCuenta(Cuenta cuenta) {
       try {
            conexion.abrirConexion();
            Statement ps = conexion.abrirConexion().createStatement();
            String up = "UPDATE cuenta SET codigo='"+cuenta.getCodigo()+"',nombre='"+cuenta.getNombre()+"'"
                    + ",tiposaldo='"+cuenta.getTipoSaldo()+"',idorden="+cuenta.getIdOrden()+",nivel="+cuenta.getNivel()+""
                    + " WHERE codigo="+cuenta.getId()+"";
            ps.executeUpdate(up);
            conexion.abrirConexion();
        } catch (Exception e) {
            System.out.println("ERROR AL MODIFICAR LA CUENTA " + e);
        }
    }

    public Cuenta obtenerCuentaU(String codigo) {
        Cuenta cuenta = new Cuenta();
        ResultSet rs = null;
        try {
            Connection accesoDB = conexion.abrirConexion();
            String sql = "SELECT * FROM cuenta Where codigo='"+codigo+"'";
            PreparedStatement ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cuenta.setId(rs.getInt("idcuenta"));
                cuenta.setCodigo(rs.getString("codigo"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setTipoSaldo(rs.getString("tiposaldo"));
                cuenta.setNivel(rs.getInt("nivel"));
            }
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return cuenta;

    }

}
