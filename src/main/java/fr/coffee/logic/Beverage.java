package fr.coffee.logic;

/**
 * Created by cafetux on 28/07/2016.
 */
public class Beverage {
    private BeverageType type;
    private int priceInCents;
    private String makerCode;
    private int nbSugars=0;

    public Beverage(BeverageType type, int priceInCents, String makerCode,int nbSugars) {
        this.type = type;
        this.priceInCents = priceInCents;
        this.makerCode = makerCode;
        this.nbSugars = nbSugars;
    }

    @Override
    public String toString() {
        StringBuilder makerInstruction = new StringBuilder();
        makerInstruction.append(makerCode);
        makerInstruction.append(":");
        if(nbSugars>0) {
            makerInstruction.append(nbSugars);
            makerInstruction.append(":");
            makerInstruction.append("0");
        }else{
            makerInstruction.append(":");
        }
        return makerInstruction.toString();   }

    public boolean costMoreThan(int moneyInCents) {
        return priceInCents>moneyInCents;
    }
}
