package fr.coffee.command;


public class BeverageCommand {
    private BeverageType beverageType;
    private int nbSugars=0;
    private final boolean needExtraHot;

    public BeverageCommand(BeverageType type,int nbSugars, boolean needExtraHot) {
        this.beverageType = type;
        this.nbSugars = nbSugars;
        this.needExtraHot=needExtraHot;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public int getNbSugars() {
        return nbSugars;
    }

    public boolean isNeedExtraHot() {
        return needExtraHot;
    }
}
