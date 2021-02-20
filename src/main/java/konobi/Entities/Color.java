package konobi.Entities;

import java.util.function.Predicate;

public enum Color {
    BLACK,
    WHITE;

    public Color oppositeColor(){
        if (this==BLACK) return WHITE;
        return BLACK;
    }


}

