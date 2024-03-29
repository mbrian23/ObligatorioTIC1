package com.example.moviecrud.business.entities;


import javax.naming.NamingException;
import java.sql.Time;
import java.time.LocalDate;


public class Funcion {


    private Long id;


    private LocalDate fecha;


//    @Column(nullable = false)
//    private LocalDate fechaFinal;

    private Time horaFuncion;


    private Sala sala;


    private Pelicula pelicula;


    private Local local;

    private Long numSala;

    private boolean[][] matriz;

    public Long getNumeroSala() {
        if (this.sala.getNumeroSala() != null){
            return this.sala.getNumeroSala();
        }
        return 1l;
    }

    public String getNombreLocal(){
        if (this.local.getName() != null) {
            return this.local.getName();
        }
        return "vacio";
    }

    public String getNombrePelicula(){
        if (this.pelicula.getTitulo() != null) {
            return this.pelicula.getTitulo();
        }
        return "vacio";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcion( LocalDate fecha, Time horaFuncion) {
        this.fecha = fecha;
        this.horaFuncion = horaFuncion;
    }

    public boolean[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(boolean[][] matriz) {
        this.matriz = matriz;
    }

    public void reservaButaca(int fila, int columna){
      matriz[fila][columna] = true;
    }

    public Funcion() {
    }

    public LocalDate getFecha() {
        return fecha;
    }


    public void setFechaInicio(LocalDate fechaInicio) {
        this.fecha = fechaInicio;
    }


    public Time getHoraFuncion() {
        return horaFuncion;
    }

    public void setHoraFuncion(Time horaFuncion) {
        this.horaFuncion = horaFuncion;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Long getNumSala() {
        return numSala;
    }

    public void setNumSala(Long numSala) {
        this.numSala = numSala;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
