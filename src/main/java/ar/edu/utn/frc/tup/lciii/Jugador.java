package ar.edu.utn.frc.tup.lciii;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Jugador {
    private String nombre;
    private int puntos;
    private int partidasGanadas;

    public void aumentarPuntos(String resultado, String posicion) {
        if (resultado == "Ganador" && posicion == "Segundo")
            puntos += 4;
        if (resultado == "Ganador" && posicion == "Primero")
            puntos += 3;
        if (resultado == "Empate" && posicion == "Segundo")
            puntos += 2;
        if (resultado == "Empate" && posicion == "Primero")
            puntos ++;
        if (resultado == "Perdedor")
            puntos ++;
    }
    public void aumentarPartidas(){
        partidasGanadas++;
    }
}
