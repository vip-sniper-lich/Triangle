package org.example;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;

public class Im_tired_boss {

    public Im_tired_boss() {
        EmbeddedMediaPlayerComponent player = new EmbeddedMediaPlayerComponent();
        JFrame frame = new JFrame("Video Player");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(player);
        frame.setVisible(true);
        player.mediaPlayer().media().play("1827868969684.mp4");
    }
}