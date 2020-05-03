/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.autoreporte.vo;

import java.util.Date;
import mx.ticom.autoreporte.anotaciones.ColumnaReporte;

/**
 *
 * @author david
 */
public class DatosReporteEmpleados {
    @ColumnaReporte(nombreColumna="Fecha del contrato")
    private Date fechaContrato;
    
    @ColumnaReporte(nombreColumna="Nombre del Empleado")
    private String empleado;
    
    @ColumnaReporte(nombreColumna="ID Empleado")
    private Integer id;   

    /**
     * @return the fechaContrato
     */
    public Date getFechaContrato() {
        return fechaContrato;
    }

    /**
     * @param fechaContrato the fechaContrato to set
     */
    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    /**
     * @return the empleado
     */
    public String getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
