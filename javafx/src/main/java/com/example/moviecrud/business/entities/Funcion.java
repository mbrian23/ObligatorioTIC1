package com.example.moviecrud.business.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private LocalDate fecha;


//    @Column(nullable = false)
//    private LocalDate fechaFinal;

    @Column(nullable = false)
    private Time horaFuncion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idSala")
    private Sala sala;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nombre_Local")
    private Local local;

    private Long numSala;

    private boolean [][] matriz;

    public Long getNumeroSala() {
        if (sala.getNumeroSala() != null){
            return sala.getNumeroSala();
        }
        return 0l;
    }

    public String getNombreLocal(){
        if (local.getName() != null) {
            return local.getName();
        }
        return "vacio";
    }

    public String getNombrePelicula(){
        if (pelicula.getTitulo() != null) {
            return pelicula.getTitulo();
        }
        return "vacio";
    }

    public boolean[][] matriz(int fila,int columna){
        return new boolean[fila][columna];
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
        this.matriz = matriz(7, 6);
    }


    public void reservaButaca(int fila, int columna){
     //   matriz(sala.getFilas().intValue(),sala.getColumnas().intValue())[fila][columna] = false;
    }

    public Funcion() {
    }

    public LocalDate getFechaInicio() {
        return fecha;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fecha = fecha;
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
}
