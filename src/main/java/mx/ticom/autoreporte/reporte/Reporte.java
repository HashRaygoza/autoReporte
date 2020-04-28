/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.autoreporte.reporte;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import mx.ticom.autoreporte.anotaciones.ColumnaReporte;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author david
 */
public class Reporte {
    
    
    private void crearEncabezado(Row fila, Class claseDatos){
        Field[] campos = claseDatos.getDeclaredFields();
        int numCelda = 0;
        
        for(Field campo : campos){
            ColumnaReporte columna = campo.getAnnotation(ColumnaReporte.class);
            if(columna != null) {
                Cell celda = fila.createCell(numCelda);
                celda.setCellValue( columna.nombreColumna() );
                numCelda++;
            }
        }
    }
    
    public <TipoGenerico> void agregarDatos(Row fila, TipoGenerico dato) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Class clase = dato.getClass();
        Field[] campos = clase.getDeclaredFields();
        int numCelda = 0;
        
        for(Field campo : campos){
            ColumnaReporte columna = campo.getAnnotation(ColumnaReporte.class);
            Cell celda = fila.createCell(numCelda);
            
            if(columna != null){
                this.agregarDato(celda, PropertyUtils.getSimpleProperty(dato, campo.getName()));
                numCelda++;
            }            
        }        
    }
    
    public void agregarDato(Cell celda, Object dato){
        if(dato instanceof BigDecimal ){
            BigDecimal d = (BigDecimal) dato;
            celda.setCellValue(d.doubleValue());
        }
        
        if( dato instanceof Integer) {
            Integer i = (Integer) dato;
            celda.setCellValue(i);
        }
        
        if( dato instanceof String) {
            String i = (String) dato;
            celda.setCellValue(i);
        }
        
        if( dato instanceof Date) {
            Date i = (Date) dato;
            celda.setCellValue(i);
        }        
    }
}
