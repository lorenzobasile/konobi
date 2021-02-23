package konobi.Entities;

import java.io.InputStream;
import java.io.OutputStream;

public class Player {
    private Color color;
    private final String name;
    private InputStream inputStream=System.in;
    private OutputStream outputStream=System.out;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }


    public Player(Color color, String name, InputStream inputStream, OutputStream outputStream) {
        this.color = color;
        this.name = name;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
    public Player(Color color, String name){
        this.color=color;
        this.name=name;
    }


    public String getName() {
        return name;
    }


    public Color getColor() {
        return color;
    }

    public void switchColorsWith(Player otherPlayer) {
        Color temp = otherPlayer.color;
        otherPlayer.color=color;
        color=temp;
    }
}
