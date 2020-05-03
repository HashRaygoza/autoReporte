/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.autoreporte;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ticom.autoreporte.dao.FuenteDatosDAO;
import mx.ticom.autoreporte.reporte.Reporte;
import mx.ticom.autoreporte.vo.DatosReporte;


/**
 *
 * @author david
 */
public class App {
    static public void main(String[] args) {
        try {
            FuenteDatosDAO fuenteDatosDAO = new FuenteDatosDAO();
            ArrayList<DatosReporte> datos = fuenteDatosDAO.generarDatos();
            
            Reporte reporte = new Reporte();
            
            reporte.crearPaginaReporte(datos, DatosReporte.class, "Reporte de Productos");
            reporte.grabarReporte(new File("reporte.xlsx"));
            
            
        } catch (ParseException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
