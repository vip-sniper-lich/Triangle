package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.concurrent.CountDownLatch;

class Vector3DTest {

    @Test
    @DisplayName("Проверка конструктора по умолчанию")
    void defaultConstructor() {
        Vector3D vector = new Vector3D();
        assertEquals(0, vector.getX());
        assertEquals(0, vector.getY());
        assertEquals(0, vector.getZ());
    }

    @Test
    @DisplayName("Проверка конструктора с параметрами")
    void parameterizedConstructor() {
        Vector3D vector = new Vector3D(1.5, 2.5, 3.5);
        assertEquals(1.5, vector.getX());
        assertEquals(2.5, vector.getY());
        assertEquals(3.5, vector.getZ());
    }

    @Test
    @DisplayName("Проверка геттеров и сеттеров")
    void gettersAndSetters() {
        Vector3D vector = new Vector3D();
        vector.setX(10);
        vector.setY(20);
        vector.setZ(30);

        assertEquals(10, vector.getX());
        assertEquals(20, vector.getY());
        assertEquals(30, vector.getZ());
    }

    @Test
    @DisplayName("Проверка вычисления длины вектора")
    void lengthCalculation() {
        Vector3D vector = new Vector3D(3, 4, 0);
        assertEquals(5, vector.getLength(), 0.0001);
    }

    @Test
    @DisplayName("Проверка скалярного произведения")
    void scalarProduct() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(4, 5, 6);
        double expected = 1*4 + 2*5 + 3*6;
        assertEquals(expected, Vector3D.scaleMultiplay(v1, v2));
    }

    @Test
    @DisplayName("Проверка равенства векторов")
    void vectorsEquality() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(1, 2, 3);
        Vector3D v3 = new Vector3D(4, 5, 6);

        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
    }

    @Test
    @DisplayName("Проверка неравенства векторов")
    void vectorsInequality() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(1, 2, 4);

        assertFalse(v1.equals(v2));
    }
    @DisplayName("GreenMina")
    @Test
    public void playVideoTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1); // Защелка для ожидания

        SwingUtilities.invokeLater(() -> {
            EmbeddedMediaPlayerComponent player = new EmbeddedMediaPlayerComponent();
            JFrame frame = new JFrame("Video Player");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрыть только окно, не завершая JVM
            frame.add(player);
            frame.setVisible(true);

            player.mediaPlayer().media().play("1827868969684.mp4");

            // При закрытии окна уменьшаем защелку
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    player.mediaPlayer().release();
                    latch.countDown();
                }
            });
        });

        latch.await();
    }
}