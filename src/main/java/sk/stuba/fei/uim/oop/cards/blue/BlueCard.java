package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Random;

public abstract class BlueCard extends Card implements CardProbability, CardPlacement {
    private Random randomGenerator;

    protected BlueCard(String name, Random randomGenerator) {
        super(name);
        this.randomGenerator = randomGenerator;
    }

    @Override
    public String getName() {
        return TxtDef.ANSI_DARK_BLUE + super.getName() + TxtDef.ANSI_RESET;
    }

    @Override
    public boolean checkChance(double probability) {
        return (randomGenerator.nextDouble() < probability);
    }

    @Override
    public boolean canBePlacedOnTable(ArrayList<Card> table, Class cardType) {
        for (Card card:table) {
            if(cardType.isInstance(card)) {
                return false;
            }
        }
        return true;
    }
}
