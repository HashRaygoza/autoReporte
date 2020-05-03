/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.autoreporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ticom.autoreporte.dao.FuenteDatosDAO;
import mx.ticom.autoreporte.reporte.Reporte;
import mx.ticom.autoreporte.vo.DatosReporte;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
            
            try (XSSFWorkbook libro = reporte.crearLibro()) {
                Sheet pagina = reporte.crearPagina(libro);
                int numeroRow = 0;
                Row encabezado = pagina.createRow(numeroRow);
                reporte.crearEncabezado(encabezado, DatosReporte.class);
                numeroRow = 1;
                for(DatosReporte d : datos){
                    Row fila = pagina.createRow(numeroRow);
                    reporte.agregarDatos(fila, d);
                    numeroRow++;
                }
                File archivo = new File("reporte.xlsx");
                try (FileOutputStream salida = new FileOutputStream(archivo)) {                
                    libro.write(salida);
                }
            }            
            
        } catch (ParseException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
