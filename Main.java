/**
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * @author jiio2
 *16/03/2021 - 15:54:18
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Object[] options = {"HashMap",// creamos un array de opciones
                    "TreeMap", "LinkedHashMap"};
        Object[] functions = {"Agregar producto", "Mostrar categoría del producto",
        		"Mostrar datos del producto", "Mostrar datos del producto ordenados",
        		"Mostrar Inventario", "Mostrar Inventario Ordenado", "Salir"};
        
        int map =JOptionPane.showOptionDialog(null,
                    "¿Qué tipo de MAP quiere usar?",// pregunamos el tipo de MAP
                    "Tipo de MAP",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        try {
            File myObj = new File("ListadoProducto.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
       
       int acciones = 0;
       
       while(acciones != 6) {
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
    	   System.out.println("Se han agradado los productos");
       } else if(respuesta == 1) {
    	   // mostrar categoría del producto
    	   System.out.println("La categoría es: ");
       }else if(respuesta == 2) {
    	   //Mostrar datos del producto
    	   System.out.println("Los datos del producto son: ");
       }else if(respuesta == 3) {
    	   // mostrar datos del producto ordenados
    	   System.out.println("Los datos en orden son:");
       }else if(respuesta == 4) {
    	   // se imprime el inventario
    	   System.out.println("El inventario es: ");
       }else if(respuesta == 5) {
    	   //inventario ordenado
    	   System.out.println("El inventario ordenado es: ");
       }else{
    	   acciones = 6;

    	   // se finaliza el programa 
       }
        
   }
	
	}
}
