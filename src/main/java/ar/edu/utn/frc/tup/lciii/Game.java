package ar.edu.utn.frc.tup.lciii;

import lombok.*;

import java.util.Scanner;

@Data
@ToString
public class Game {
    String[][] mapa;
    String grafico;
    int x;
    int y;
    
    public Game(){
        mapa = new String[3][3];
        limpiarMapa();
        grafico =  "   1 2 3 " + System.lineSeparator() +
                   "1 | | | |" + System.lineSeparator() +
                   "2 | | | |" + System.lineSeparator() +
                   "3 | | | |";
    }
    public void iniciar(Jugador jugadorX, Jugador jugadorO, String inicio){
        limpiarMapa();
        System.out.println(grafico);
        Scanner sc = new Scanner(System.in);
        Jugador jugadorActual;
        Jugador jugadorSiguiente;
        String tipo;
        if (inicio.equals("X")){
            jugadorActual = jugadorX;
            jugadorSiguiente = jugadorO;
            tipo = "X";
        }

        else{
            jugadorActual = jugadorO;
            jugadorSiguiente = jugadorX;
            tipo = "O";
        }
        for (int i = 0; i < 9; i++) {
            do {
                System.out.printf(jugadorActual.getNombre() + " elija una columna: ");
                x = sc.nextInt() -1;
                System.out.printf(jugadorActual.getNombre() + " elija una fila: ");
                y = sc.nextInt() -1;
            }while (!movimiento(x, y, tipo));
            if (taTeTi(tipo)){
                ganador(jugadorActual, jugadorSiguiente, inicio, tipo);
                return;
            }
            Jugador var = jugadorActual;
            jugadorActual = jugadorSiguiente;
            jugadorSiguiente = var;
            if (tipo.equals("X"))
                tipo = "O";
            else
                tipo = "X";
        }
        empate(jugadorX, jugadorO, inicio);
    }

    private void limpiarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = " ";
            }
        }
    }

    private void empate(Jugador jugadorX, Jugador jugadorO, String inicio) {
        System.out.println("Empate");
        if (inicio.equals("X")){
            jugadorX.aumentarPuntos("Empate", "Primero");
            jugadorO.aumentarPuntos("Empate", "Segundo");
        }else{
            jugadorX.aumentarPuntos("Empate", "Segundo");
            jugadorO.aumentarPuntos("Empate", "Primero");
        }

    }

    private void ganador(Jugador ganador, Jugador perdedor, String inicio, String tipo) {
        System.out.println("Ganador: " + ganador.getNombre());
        if (inicio.equals(tipo)){
            ganador.aumentarPuntos("Ganador", "Primero");
            perdedor.aumentarPuntos("Perdedor", "Segundo");
        }else{
            ganador.aumentarPuntos("Ganador", "Segundo");
            perdedor.aumentarPuntos("Perdedor", "Primero");
        }
        ganador.aumentarPartidas();
    }

    public boolean taTeTi(String tipo) {
        if(mapa[0][0].equals(tipo)  && mapa[0][1].equals(tipo) && mapa[0][2].equals(tipo)){
            return true;
        }
        if(mapa[1][0].equals(tipo)  && mapa[1][1].equals(tipo) && mapa[1][2].equals(tipo)){
            return true;
        }
        if(mapa[2][0].equals(tipo)  && mapa[2][1].equals(tipo) && mapa[2][2].equals(tipo)){
            return true;
        }
        if(mapa[0][0].equals(tipo)  && mapa[1][0].equals(tipo) && mapa[2][0].equals(tipo)){
            return true;
        }
        if(mapa[0][1].equals(tipo)  && mapa[1][1].equals(tipo) && mapa[2][1].equals(tipo)){
            return true;
        }
        if(mapa[0][2].equals(tipo)  && mapa[1][2].equals(tipo) && mapa[2][2].equals(tipo)){
            return true;
        }
        if(mapa[0][0].equals(tipo)  && mapa[1][1].equals(tipo) && mapa[2][2].equals(tipo)){
            return true;
        }
        if(mapa[0][2].equals(tipo)  && mapa[1][1].equals(tipo) && mapa[2][0].equals(tipo)){
            return true;
        }
        return false;
    }

    public boolean movimiento( int x, int y, String tipo){
        if (x < 0 || x > 2 || y < 0 || y > 2){
            System.out.println("Esta posicion se sale del borde! Ingrese otra");
            return false;
        }
        else if (!mapa[x][y].equals(" ")){
            System.out.println("Esta posición ya está utilizada! Ingrese otra");
            return false;
        }
        else {
            mapa[x][y] = tipo;
            grafico =  "   1 2 3 " + System.lineSeparator() +
                    "1 |" + mapa[0][0] +"|" + mapa[1][0] + "|" + mapa[2][0] +"|" + System.lineSeparator() +
                    "2 |" + mapa[0][1] +"|" + mapa[1][1] + "|" + mapa[2][1] +"|" + System.lineSeparator() +
                    "3 |" + mapa[0][2] +"|" + mapa[1][2] + "|" + mapa[2][2] +"|";
            System.out.println(grafico);
            return true;
        }
    }
}
