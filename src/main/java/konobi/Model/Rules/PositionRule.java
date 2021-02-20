package konobi.Model.Rules;

import konobi.Model.Entities.Board;
import konobi.Model.Entities.Cell;
import konobi.Model.Entities.Color;

public class PositionRule implements Rule {

    @Override
    public boolean isValid(Board board, Cell cell, Color stoneColor) {
        WeakConnectionRule ruleOne = new WeakConnectionRule();
        CrossCutRule ruleTwo = new CrossCutRule();

        return ruleOne.isValid(board, cell, stoneColor) &&
                ruleTwo.isValid(board, cell, stoneColor);


    }
}
