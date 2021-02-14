package konobi;

public enum Stone {
    BLACK,
    WHITE;

    public Stone oppositeColor(){
        if (this==BLACK) return WHITE;
        return BLACK;
    }
}

