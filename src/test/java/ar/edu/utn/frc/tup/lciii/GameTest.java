package ar.edu.utn.frc.tup.lciii;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class GameTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
    @Test
    public void testTaTeTiCase1(){
        Game juego = new Game();
        boolean mov;
        mov = juego.movimiento(0,0, "X");
        mov = juego.movimiento(1,0, "X");
        mov = juego.movimiento(2,0, "X");
        assertTrue(juego.taTeTi("X"));
    }
    @Test
    public void testTaTeTiCase2(){
        Game juego = new Game();
        boolean mov;
        mov = juego.movimiento(0,0, "X");
        mov = juego.movimiento(1,1, "X");
        mov = juego.movimiento(2,0, "X");
        assertFalse(juego.taTeTi("X"));
    }
    @Test
    public void testTaTeTiCase3(){
        Game juego = new Game();
        boolean mov;
        mov = juego.movimiento(0,0, "X");
        mov = juego.movimiento(1,1, "X");
        mov = juego.movimiento(2,2, "X");
        assertTrue(juego.taTeTi("X"));
    }
    @Test
    @DisplayName("Testing metodo privado sin Mockito")
    public void testTaTeTiCase4() throws Exception{
        Jugador jugadorX = new Jugador("Imanol", 0, 0);
        Jugador jugadorO = new Jugador("Bianca", 0, 0);
        Game juego = new Game();
        Method metodoPrivado = Game.class.getDeclaredMethod("ganador", Jugador.class, Jugador.class, String.class, String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(juego, jugadorX, jugadorO, "X", "X");
        assertEquals("Ganador: Imanol" + System.lineSeparator(), getOutput());
    }
    @Test
    @DisplayName("Testing metodo privado con Mockito")
    public void testTaTeTiCase5() throws Exception{
        Jugador jugadorX = mock(Jugador.class);
        Jugador jugadorO = mock(Jugador.class);
        Game juego = new Game();
        when(jugadorX.getNombre()).thenReturn("Imanol");
        when(jugadorO.getNombre()).thenReturn("Bianca");
        Method metodoPrivado = Game.class.getDeclaredMethod("ganador", Jugador.class, Jugador.class, String.class, String.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(juego, jugadorX, jugadorO, "X", "X");
        assertEquals("Ganador: Imanol" + System.lineSeparator(), getOutput());
    }
}
