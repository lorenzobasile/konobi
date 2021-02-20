package konobi.Rules;

import konobi.Entities.Board;
import konobi.Entities.Cell;
import konobi.Entities.Color;

public interface Rule {
    boolean isValid(Board board, Cell cell, Color stoneColor);
}
