/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.autoreporte.vo;

import java.math.BigDecimal;
import java.util.Date;
import mx.ticom.autoreporte.anotaciones.ColumnaReporte;


/**
 *
 * @author david
 */
public class DatosReporte {
    @ColumnaReporte(nombreColumna="Fecha")
    private Date fecha;
    
    @ColumnaReporte(nombreColumna="Nombre Producto")
    private String producto;
    
    @ColumnaReporte(nombreColumna="Inventario a la fecha")
    private Integer inventario;
    
    @ColumnaReporte(nombreColumna="Cambio estadistico (%)")
    private BigDecimal cambioEstadistico;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the inventario
     */
    public Integer getInventario() {
        return inventario;
    }

    /**
     * @param inventario the inventario to set
     */
    public void setInventario(Integer inventario) {
        this.inventario = inventario;
    }

    /**
     * @return the cambioEstadistico
     */
    public BigDecimal getCambioEstadistico() {
        return cambioEstadistico;
    }

    /**
     * @param cambioEstadistico the cambioEstadistico to set
     */
    public void setCambioEstadistico(BigDecimal cambioEstadistico) {
        this.cambioEstadistico = cambioEstadistico;
    }
}
