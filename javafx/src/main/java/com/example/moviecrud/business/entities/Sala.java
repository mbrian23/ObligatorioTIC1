package com.example.moviecrud.business.entities;





public class Sala {


    private Long id;

    private Long filas;

    private Long columnas;

    private String tipo;

    private Long numeroSala;

    private Local local;


    public String getNombreCine() {
        if (local.getCine().getNombre() != null){
            return local.getCine().getNombre();
        }
        return "vacio";
    }

    public String getNombreLocal(){
        if (local.getName() != null) {
            return local.getName();
        }
        return "vacio";
    }

    public Sala(Long filas, Long columnas, String tipo, Long numeroSala) {
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
        this.numeroSala = numeroSala;

    }

    public Sala(Long filas, Long columnas, String tipo, Long numeroSala, Local local) {
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
        this.numeroSala = numeroSala;
        this.local = local;
    }

    public Sala() {
    }

//    public String getnLocal() {
//        return nLocal;
//    }
//
//    public void setnLocal(String nLocal) {
//        this.nLocal = nLocal;
//    }

    //    public Sala( Long filas ,Long columnas, String tipo,Long numeroSala) {
//        this.filas = filas;
//        this.columnas = columnas;
//        this.tipo = tipo;
//        this.numeroSala = numeroSala;
//        this.matriz = matriz(filas.intValue(), columnas.intValue());
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Long getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Long numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Long getFilas() {
        return filas;
    }

    public void setFilas(Long filas) {
        this.filas = filas;
    }

    public Long getColumnas() {
        return columnas;
    }

    public void setColumnas(Long columnas) {
        this.columnas = columnas;
    }





}
