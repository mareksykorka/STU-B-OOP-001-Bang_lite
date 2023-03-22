package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.TxtDef;

public abstract class BrownCard extends Card {

    public BrownCard(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return TxtDef.ANSI_DARK_YELLOW + super.getName() + TxtDef.ANSI_RESET;
    }
}