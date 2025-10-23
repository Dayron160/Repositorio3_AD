package com.dayronnotario.tema01;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        System.out.println();
        System.out.println("Act 1");
        System.out.println();

        Act1 a1 = new Act1();
        Empleado1[] empleados = a1.parse("Datasets-20251016/empleados.xml");
        for (Empleado1 empleado: empleados) {
            System.out.println(empleado.toString());
            System.out.println();
        }

        System.out.println();
        System.out.println("Act 2");
        System.out.println();

        Act2 a2 = new Act2("Datasets-20251016/empleados.xml");
        a2.mostrarDepartamentos();

        System.out.println();
        System.out.println("Act 3");
        System.out.println();

        Act3 a3 = new Act3("Datasets-20251016/biblioteca.xml");
        a3.mostrarTitulos();
        a3.mostrarConteoPorGenero();

        System.out.println();
        System.out.println("Act 4");
        System.out.println();

      /*  Act4 a4 = new Act4("Datasets-20251016/pedidos.xml");
        Scanner lector = new Scanner(System.in);
        System.out.print("Introduce el ID del pedido: ");
        String id = lector.nextLine();

        a4.mostrarItemsDePedido(id);
        lector.close(); */

        System.out.println();
        System.out.println("Act 5");
        System.out.println();

        Act5 a5 = new Act5("Datasets-20251016/alumnos.json");
        a5.mostrarInfo();

        System.out.println();
        System.out.println("Act 6");
        System.out.println();

        Act6 a6 = new Act6("Datasets-20251016/alumnos.json");
        a6.mostrarNotaMaxima();
        a6.mostrarMediaMasAlta();
    }
}