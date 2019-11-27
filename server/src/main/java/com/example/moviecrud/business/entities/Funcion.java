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
    @Column(name = "idFuncion")
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


    public boolean[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(boolean[][] matriz) {
        this.matriz = matriz;
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




    public void reservaButaca(int fila, int columna){
     matriz[fila][columna] = false;
    }

    public Funcion() {
    }

    public Long getNumeroSala() {
        if (this.sala.getNumeroSala() != null){
            return this.sala.getNumeroSala();
        }
        return 3l;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fechaInicio) {
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
}
