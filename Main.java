
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * @author jiio2
 *16/03/2021
 * 
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Inventario inventario;
		// TODO Auto-generated method stub
        Object[] options = {"HashMap",// creamos un array de opciones
                    "TreeMap", "LinkedHashMap"};
        Object[] functions = {"Agregar producto", "Mostrar categoría del producto",
        		"Mostrar datos del producto", "Mostrar datos del producto ordenados",
        		"Mostrar Inventario", "Mostrar Inventario Ordenado", "Comparar tiempos de ejecución","Salir"};
        
        int map =JOptionPane.showOptionDialog(null,
                    "¿Qué tipo de MAP quiere usar?",// pregunamos el tipo de MAP
                    "Tipo de MAP",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        inventario = new Inventario("ListadoProducto.txt",map);
       
       int acciones = 0;
       
       while(acciones != 7) {
       int respuesta = JOptionPane.showOptionDialog(null,
                "¿Qué acción desea realizar?",// acciones que puede realizar el usuario
                "Acciones disponibles",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                functions,
                functions[0]);
       if(respuesta == 0) {
    	   // agregar productos 
           String cat = JOptionPane.showInputDialog(null,"Ingrese la categoría del producto:");
           String desc = JOptionPane.showInputDialog(null,"Ingrese la descripción del producto:");
           int cant = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de producto adquirido"));
           inventario.agregarProducto(cat,desc, cant);
    	   System.out.println("Se han agregado los productos");
       } else if(respuesta == 1) {
    	   // mostrar categor�a del producto
           String desc = JOptionPane.showInputDialog(null,"Ingrese la descripción del producto:");
           String categoria = inventario.getCategoria(desc);
    	   System.out.println("La categoría es: "+ categoria);
       }else if(respuesta == 2) {
    	   //Mostrar datos del producto
    	   System.out.println("Los datos del producto son: ");
           System.out.println(inventario.getDatos());
       }else if(respuesta == 3) {
    	   // mostrar datos del producto ordenados
    	   System.out.println("Los datos en orden son:");
           System.out.println(inventario.getDatosOrdenados());
       }else if(respuesta == 4) {
    	   // se imprime el inventario
    	   System.out.println("El inventario es: ");
           System.out.println(inventario.getPyC());
       }else if(respuesta == 5) {
    	   //inventario ordenado
    	   System.out.println("El inventario ordenado es: ");
           System.out.println(inventario.getPyCO());
       }else if(respuesta == 6){
           Inventario inventario1;
           long[] tiempos = new long[3];
           inventario1 = new Inventario("ListadoProducto.txt",0);
           long t0 = System.nanoTime();
           for(int i = 0; i<100; i++){
            inventario1.getDatosOrdenados();   
           }
           long t1 = System.nanoTime()-t0;
           tiempos[0] = t1/100;
           inventario1 = new Inventario("ListadoProducto.txt",1);
           t0 = System.nanoTime();
           for(int i = 0; i<100; i++){
            inventario1.getDatosOrdenados();   
           }
           t1 = System.nanoTime()-t0;
           tiempos[1] = t1/100;
           inventario1 = new Inventario("ListadoProducto.txt",2);
           t0 = System.nanoTime();
           for(int i = 0; i<100; i++){
            inventario1.getDatosOrdenados();   
           }
           t1 = System.nanoTime()-t0;
           tiempos[2] = t1/100;
           
           System.out.println("Los tiempos de ejecución promedio (en nanosegundos) fueron:\n"
                   + " Hashmap: "+ tiempos[0]+ " \n"
                           + " Treemap: "+tiempos[1]+ " \n"
                                   + " LinkedHashmap: "+tiempos[2]+" ");
       }else{
    	   acciones = 7;
    	   // se finaliza el programa 
       }
        
   }
	
	}
}
