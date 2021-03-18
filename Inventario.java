
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * 17/03/2021
 * @author javi-
 * La clase inventario es la que maneja todas las funciones.
 */
public class Inventario {

    /**
     * La clase Producto es una clase local que se usa para trabajar de forma más ordenada el inventario.
     * Además, es una visión más orientada a objetos.
     * Los atributos son la descripcion y la categoria del producto, e implementa Comparable para ordenar el inventario.
     */
    public class Producto implements Comparable{
        //Descripcion es la descripcion del producto
        String descripcion;
        //Categoria es la categoria del producto
        String categoria;
        
        /**
         * Constructor
         * @param cat es la categoria del producto
         * @param desc es la descripcion del producto
         */
        public Producto(String cat,String desc){
            this.descripcion = desc;
            this.categoria = cat;
        }

        /**
         * Devuelve la categoria del producto
         * @return la categoria del producto
         */
        public String getCategoria() {
            return categoria;
        }

        /**
         * Actualiza la categoria del producto
         * @param categoria la categoria del producto
         */
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        /**
         * Devuelve la descripcion del producto
         * @return la descripcion del producto
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Actualiza la descripcion del producto
         * @param descripcion la descripcion del producto
         */
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        /**
         * Devuelve la categoria y descripcion del producto concatenados
         * @return Las caracteristicas del producto
         */
        public String describe(){
            return "Categoria=" + categoria+ ", descripcion=" + descripcion;
        }

        /**
         * Devuelve la categoria, la descripcion, y los disponibles concatenados (esta es una de las razones para tener esta clase local)
         * @return Las caracteristicas y los disponibles del producto
         */
        @Override
        public String toString() {
            return "Categoria=" + categoria+ ", descripcion=" + descripcion + ", cantidad = "+inventario.get(this);
        }

        /**
         * Compara dos productos primero por su categoria, y luego por su descripcion
         * @param t el segundo producto a comparar
         * @return menor a 0 si el primer producto es mayor, mayor a cero si el segundo lo es, 0 si son iguales.
         */
        @Override
        public int compareTo(Object t) {
            Producto p = (Producto) t;
            int w = this.categoria.compareTo(p.getCategoria());
            //Sorting by first name if last name is same d
            return w == 0 ? this.descripcion.compareTo(p.getDescripcion()) : w;
        }
        
        
    }
    //inventario es un Mapa que mapea productos a enteros, es decir, cada producto a su cantidad.
    private Map<Producto,Integer> inventario;

    /**
     * Constructor
     * @param filename es la ruta del archivo txt donde está la lista de productos
     * @param tipo es el tipo de mapa a usar. 0 para Hashmap, 1 para Treemap, 2 para LinkedHashmap
     */
    public Inventario(String filename, int tipo) {
        switch(tipo){
            case 0:
                inventario = new HashMap<Producto, Integer>();
                break;
            case 1:
                inventario = new TreeMap<Producto,Integer>();
                break;
            case 2:
                inventario = new LinkedHashMap<Producto,Integer>();
                break;
            default:
                break;
        }
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] prod = parseData(data);
              Producto producto = new Producto(prod[0].trim(),prod[1].trim());
              inventario.put(producto, 10);
            }
            
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    
    private String[] parseData(String str){	
    String categoria, producto;
    Scanner lineScanner = new Scanner(str);
    lineScanner.useDelimiter("\\|");
    String[] r = new String[2];
    categoria = lineScanner.next();
    producto = lineScanner.next();
    r[0] = categoria;
    r[1] = producto;
    lineScanner.close();
    return r;
    
    }
    
    /**
     * Agrega un producto al inventario
     * @param cat es la categoria del producto
     * @param desc es la descripcion del producto
     * @param cantidad es la cantidad de producto a agregar
     */
    public void agregarProducto(String cat, String desc, int cantidad)
	{	
            Producto producto = new Producto(cat,desc);
		if(inventario.get(producto) == null)
			inventario.put(producto, 0);
		cantidad += inventario.get(producto);
		inventario.put(producto, cantidad);
	}
    
    /**
     * Devuelve la categoria de un producto a partir de su descripcion
     * @param desc es la descripcion del producto
     * @return la categoria del producto con esa descripcion
     */
    public String getCategoria(String desc){
        Iterator<Producto> itr = inventario.keySet().iterator();
        while(itr.hasNext()){
            Producto p = itr.next();
            if(p.getDescripcion().equalsIgnoreCase(desc)){
                return p.getCategoria();
            }
        }
        return "Error: producto desconocido";
    }
    
    /**
     * Devuelve la lista de todos los productos con su cantidad
     * @return
     */
    public String getDatos(){
        String res = "";
        Iterator<Producto> itr = inventario.keySet().iterator();
        while(itr.hasNext()){
        res += itr.next().toString()+ "\n ";
        }
        return res;
    }
    
    /**
     * Devuelve la lista de todos los productos y su cantidad ordenada por categoria
     * @return
     */
    public String getDatosOrdenados(){
        String res = "";
        SortedSet<Producto> ss = new TreeSet<>(inventario.keySet());
        for(Producto p : ss){
        res += p.toString()+ "\n ";
        }
        return res;
    }
    
    /**
     * Devuelve las caracteristicas de todos los productos 
     * @return
     */
    public String getPyC(){
        String res = "";
        Iterator<Producto> itr = inventario.keySet().iterator();
        while(itr.hasNext()){
        res += itr.next().describe()+ "\n ";
        }
        return res;
    }

    /**
     * Devuelve las caracteristicas de todos los productos ordenada por categoria.
     * @return
     */
    public String getPyCO(){
        String res = "";
        SortedSet<Producto> ss = new TreeSet<>(inventario.keySet());
        for(Producto p : ss){
            if(inventario.get(p)!=0){
                res += p.describe()+ "\n ";
            }  
        }
        return res;
    }
}
