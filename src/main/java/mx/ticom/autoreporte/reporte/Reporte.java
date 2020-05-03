/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.autoreporte.reporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import mx.ticom.autoreporte.anotaciones.ColumnaReporte;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author david
 */
public class Reporte {

    private final XSSFWorkbook libro;

    public Reporte() {
        libro = new XSSFWorkbook();
    }

    public void grabarReporte(File archivo) throws FileNotFoundException, IOException {
        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            libro.write(salida);
        }

    }

    public <TipoGenerico> void crearPaginaReporte(ArrayList<TipoGenerico> datos, Class tipoGenerico, String nombrePagina) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Sheet pagina = libro.createSheet(nombrePagina);
        int numFila = 0;

        Row encabezado = pagina.createRow(numFila);
        this.crearEncabezado(encabezado, tipoGenerico);

        numFila = 1;
        for (TipoGenerico dato : datos) {
            Row filaDato = pagina.createRow(numFila);
            this.agregarDatos(filaDato, dato);
            numFila++;
        }
        
        this.redimencionar(pagina, tipoGenerico);
    }


    protected void crearEncabezado(Row fila, Class claseDatos) {
        Field[] campos = claseDatos.getDeclaredFields();
        int numCelda = 0;

        for (Field campo : campos) {
            ColumnaReporte columna = campo.getAnnotation(ColumnaReporte.class);
            if (columna != null) {
                Cell celda = fila.createCell(numCelda);
                celda.setCellValue(columna.nombreColumna());
                numCelda++;
            }
        }
    }
    
    protected void redimencionar(Sheet pagina, Class claseDatos) {
        Field[] campos = claseDatos.getDeclaredFields();
        int numeroColumnas=0;
        
        for (Field campo : campos) {
            ColumnaReporte columna = campo.getAnnotation(ColumnaReporte.class);
            if (columna != null) {
                numeroColumnas++;
            }
        }
        
        numeroColumnas++;
        
        for(int i=0; i < numeroColumnas; i++) {
            pagina.autoSizeColumn(i);
        }
        
        
    }

    protected <TipoGenerico> void agregarDatos(Row fila, TipoGenerico dato) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class clase = dato.getClass();
        Field[] campos = clase.getDeclaredFields();
        int numCelda = 0;

        for (Field campo : campos) {
            ColumnaReporte columna = campo.getAnnotation(ColumnaReporte.class);
            Cell celda = fila.createCell(numCelda);

            if (columna != null) {
                this.agregarDato(celda, PropertyUtils.getSimpleProperty(dato, campo.getName()));
                numCelda++;
            }
        }
    }

    protected void agregarDato(Cell celda, Object dato) {
        if (dato instanceof BigDecimal) {
            BigDecimal d = (BigDecimal) dato;
            celda.setCellValue(d.doubleValue());
        }

        if (dato instanceof Integer) {
            Integer i = (Integer) dato;
            celda.setCellValue(i);
        }

        if (dato instanceof String) {
            String i = (String) dato;
            celda.setCellValue(i);
        }

        if (dato instanceof Date) {
            CellStyle cellStyle = libro.createCellStyle();
            CreationHelper createHelper = libro.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));

            Date i = (Date) dato;
            celda.setCellValue(i);
            celda.setCellStyle(cellStyle);
        }
    }
}
