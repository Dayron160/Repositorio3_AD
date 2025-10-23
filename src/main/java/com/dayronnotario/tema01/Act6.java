package com.dayronnotario.tema01;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Act6 {
    private List<Alumno2> alumnos;

    public Act6(String ruta) {
        try (FileReader reader = new FileReader(ruta)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Alumno2>>(){}.getType();
            alumnos = gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Muestra la nota más alta de cada alumno
     * @return true o false dependiendo de si se ha podido o no hacer lo deseado
     */
    public boolean mostrarNotaMaxima() {
        if (alumnos == null || alumnos.isEmpty()) {
            return false;
        }

        System.out.println("Nota más alta por alumno:");
        System.out.printf("%-20s | %-50s | %-5s%n", "Alumno", "Asignatura", "Nota");
        System.out.println("-------------------------------------------------------------------------------");

        for (Alumno2 a : alumnos) {
            Nota max = a.getNotaMaxima();
            System.out.printf("%-20s | %-50s | %-5.2f%n", a.getNombre(), max.getAsignatura(), max.getNota());
        }
        return true;
    }

    /**
     * Muestra que alumno tiene la media más alta y esta misma
     * @return true o false dependiendo de si se ha podido o no hacer lo deseado
     */
    public boolean mostrarMediaMasAlta() {
        if (alumnos == null || alumnos.isEmpty()) {
            return false;
        }

        Alumno2 mejor = alumnos.get(0);
        for (Alumno2 a : alumnos) {
            if (a.getNotaMedia() > mejor.getNotaMedia()) {
                mejor = a;
            }
        }

        System.out.println("\nAlumno con la nota media más alta:");
        System.out.printf("%s - Media: %.2f%n", mejor.getNombre(), mejor.getNotaMedia());
        return true;
    }
}

class Alumno2 {
    private String id;
    private String nombre;
    private boolean matriculado;
    private String fechaNacimiento;
    private Nota[] notas;

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean isMatriculado() { return matriculado; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public Nota[] getNotas() { return notas; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNotas(Nota[] notas) {
        this.notas = notas;
    }

    /**
     * Nos devuelve la nota máxima del alumno
     * @return Objeto Nota
     */
    public Nota getNotaMaxima() {
        Nota max = notas[0];
        for (Nota n : notas) {
            if (n.getNota() > max.getNota()) {
                max = n;
            }
        }
        return max;
    }

    /**
     * Nos devuelve la media del alumno en formato double
     * @return media
     */
    public double getNotaMedia() {
        double suma = 0;
        for (Nota n : notas) {
            suma += n.getNota();
        }
        return suma / notas.length;
    }
}

class Nota {
    private String asignatura;
    private double nota;

    public String getAsignatura() {
        return asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}