package ar.edu.utn.frc.tup.lciii;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuGame {
    Jugador jugadorX;
    Jugador jugadorO;
    public void main(){
        Scanner sc = new Scanner(System.in);
        String next;
        System.out.println("Bienvenido al juego!! TA-TE-TI");
        System.out.println("Ingrese el nombre del Jugador X");
        String nombre = sc.nextLine();
        jugadorX = new Jugador(nombre, 0, 0);
        System.out.println("Ingrese el nombre del Jugador O");
        nombre = sc.nextLine();
        jugadorO = new Jugador(nombre, 0, 0);
        System.out.println("Iniciando juego...");
        Game juego = new Game();
        int inicio = 1;
        do {
            if (inicio % 2 == 0)
                juego.iniciar(jugadorX, jugadorO, "O");
            else
                juego.iniciar(jugadorX, jugadorO, "X");
            System.out.printf("Desea jugar de nuevo?");
            next = sc.nextLine();
            if (next.equals("no")){
                resultado();
                break;
            }
            inicio++;
        }while (true);

    }

    private void resultado() {
        System.out.println("---------------GAME OVER-------------");
        System.out.println("Puntos de " + jugadorX.getNombre() + " : " + jugadorX.getPuntos() + " (" + jugadorX.getPartidasGanadas() + " partidos ganados)");
        System.out.println("Puntos de " + jugadorO.getNombre() + " : " + jugadorO.getPuntos() + " (" + jugadorO.getPartidasGanadas() + " partidos ganados)");
        System.out.println("---------------GAME OVER-------------");

    }
}
