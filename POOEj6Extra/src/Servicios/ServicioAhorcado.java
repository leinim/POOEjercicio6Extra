/*
Juego Ahorcado: Crear una clase Ahorcado (como el juego), la cual deberá contener
como atributos, un vector con la palabra a buscar, la cantidad de letras encontradas y la
cantidad jugadas máximas que puede realizar el usuario. Definir los siguientes métodos
con los parámetros que sean necesarios:
 Constructores, tanto el vacío como el parametrizado.
 Metodo crearJuego(): le pide la palabra al usuario y cantidad de jugadas máxima.
Con la palabra del usuario, pone la longitud de la palabra, como la longitud del
vector. Después ingresa la palabra en el vector, letra por letra, quedando cada letra
de la palabra en un índice del vector. Y también, guarda en cantidad de jugadas
máximas, el valor que ingresó el usuario y encontradas en 0.
 Método longitud(): muestra la longitud de la palabra que se debe encontrar. Nota:
buscar como se usa el vector.length.
 Método buscar(letra): este método recibe una letra dada por el usuario y busca sila
letra ingresada es parte de la palabra o no. También informará si la letra estaba o no.
 Método encontradas(letra): que reciba una letra ingresada por el usuario y muestre
cuantas letras han sido encontradas y cuantas le faltan. Este método además deberá
devolver true si la letra estaba y false si la letra no estaba, ya que, cada vez que se
busque una letra que no esté, se le restará uno a sus oportunidades.
 Método intentos(): para mostrar cuantas oportunidades le queda al jugador.
 Método juego(): el método juego se encargará de llamar todos los métodos
previamente mencionados e informará cuando el usuario descubra toda la palabra o
se quede sin intentos. Este método se llamará en el main.
 */
package Servicios;

import Entidades.Ahorcado;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Mile
 */
public class ServicioAhorcado {
    
    public Ahorcado crearJuego(){
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese la palabra a adivinar");
        String palabra = leer.nextLine();
        System.out.println("Ingrese la cantidad máxima de turnos");
        int turnos = leer.nextInt();
        
        int longi = palabra.length();
        String[] palabraVector = new String[longi];
        for (int i = 0; i < longi; i++){
            palabraVector[i] = palabra.substring(i, i+1);
        }
        
        return new Ahorcado(palabraVector, 0, turnos);
    }
    
    public void longitud(Ahorcado a){
        System.out.println("Longitud de la palabra: " + a.getPalabra().length);
        
    }
    
    public void buscar(String l, Ahorcado a){
        String palabra = "";
        
        for (int i = 0; i < a.getPalabra().length; i++){
           palabra += a.getPalabra()[i];
        }
        
        int longi = palabra.length();
        int contador = 0;
        for (int i = 0; i < longi; i++){
            if (palabra.substring(i, i+1).equals(l)){
                contador++;                                
            }
        }
        if (contador > 0){
                System.out.println("La letra se encuentra " + contador + " vez/veces en la palabra.");                                
            } else {
                System.out.println("La letra no se encuentra en la palabra");
            }
                
    }
    
    public void encontradas(String l, Ahorcado a){
        boolean encontrada = false;
        String palabra = "";
        
        for (int i = 0; i < a.getPalabra().length; i++){
           palabra += a.getPalabra()[i];
        }
        int longi = a.getPalabra().length;
        for (int i = 0; i < longi; i++){
            if (palabra.substring(i, i+1).equals(l)){                
                a.setLetrasEncontradas(a.getLetrasEncontradas()+1); 
                encontrada = true;
                
            } 
        }
        if (encontrada == false){
            a.setCantidadJugadas(a.getCantidadJugadas()-1);
            System.out.println("Ha perdido un turno. Siga intentando.");
        } else {
            System.out.println("Letras encontradas: " + a.getLetrasEncontradas());
            System.out.println("Letras restantes: " + (longi - a.getLetrasEncontradas()));
        }                  
        
    }
    
    public void intentos(Ahorcado a){
        
        System.out.println("Le quedan " + a.getCantidadJugadas() + " turnos.");
    }
    
    public void juego(Ahorcado a, String l){
        longitud(a);
        buscar(l, a);
        encontradas(l, a);
        intentos(a);
        
        String palabra = "";
        
        for (int i = 0; i < a.getPalabra().length; i++){
           palabra += a.getPalabra()[i];
        }
        
        int longi = a.getPalabra().length;
        if (a.getCantidadJugadas() == 0 && a.getLetrasEncontradas() == longi){
            System.out.println("Ha ganado justo en su ultimo turno. La palabra es: " + palabra );
        } else if (a.getCantidadJugadas() == 0 && a.getLetrasEncontradas() < longi) {
            System.out.println("Se ha quedado sin turnos.");
        } else if (a.getCantidadJugadas() > 0 && a.getLetrasEncontradas() == longi){
            System.out.println("Ha encontrado la palabra: " + palabra);
        } else {
            System.out.println("Ingrese la siguiente letra");
        }       
        
    }
    
}
