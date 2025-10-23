package com.dayronnotario.tema01;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Act2 {
    private List<Empleado2> empleados = new ArrayList<>();
    private File xmlFile;
    public Act2(String ruta) {
        xmlFile = new File(ruta);
        mapearXML();
    }

    /**
     * Pasa los datos del archivo xml a una lista
     */
    private void mapearXML() {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("empleado");

            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;

                    String id = elem.getAttribute("id");
                    String nombre = elem.getElementsByTagName("nombre").item(0).getTextContent();
                    String departamento = elem.getElementsByTagName("departamento").item(0).getTextContent();
                    double salario = Double.parseDouble(elem.getElementsByTagName("salario").item(0).getTextContent());
                    String currency = elem.getElementsByTagName("salario").item(0).getAttributes().getNamedItem("moneda").getNodeValue();

                    empleados.add(new Empleado2(id, nombre, departamento, salario));
                }
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Muestra el número de empleados y el salario medio de cada departamento
     * @return true o false dependiendo de si puede o no hacer lo deseado
     */
    public boolean mostrarDepartamentos() {
        if (empleados.isEmpty()) {
            return true;
        }

        Map<String, Double> salarioMedio = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado2::getDepartamento,
                        Collectors.averagingDouble(Empleado2::getSalario)
                ));

        Map<String, Long> conteo = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado2::getDepartamento,
                        Collectors.counting()
                ));

        System.out.printf("%-15s | %-13s | %-15s%n", "Departamento", "Nº empleados", "Salario medio");
        System.out.println("-----------------------------------------------------------");

        for (String dept : salarioMedio.keySet()) {
            System.out.printf("%-15s | %-13d | %-15.2f%n", dept, conteo.get(dept), salarioMedio.get(dept));
        }
        return true;
    }
}

class Empleado2 {
    private String id;
    private String nombre;
    private String departamento;
    private double salario;

    public Empleado2(String id, String nombre, String departamento, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Devuelve un String formateado con la información del empleado
     * @return String formateado
     */
    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f €", nombre, departamento, salario);
    }
}