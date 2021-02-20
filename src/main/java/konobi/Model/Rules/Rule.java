package konobi.Model.Rules;

import konobi.Model.Entities.Board;
import konobi.Model.Entities.Cell;
import konobi.Model.Entities.Color;

public interface Rule {
    boolean isValid(Board board, Cell cell, Color stoneColor);
}
