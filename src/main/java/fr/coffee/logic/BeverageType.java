package fr.coffee.logic;


public enum BeverageType {

    // One tea is 0,4 euro, a coffee is 0,6 euro, a chocolate is 0,5 euro.
    COFFEE("C",60), TEA("T",40), CHOCOLATE("H",50), ORANGE_JUICE("O",60);

    private String makerCode;
    private int priceInCents;

    BeverageType(String makerCode,int priceInCents) {
        this.priceInCents = priceInCents;
        this.makerCode = makerCode;
    }

    public String getMakerCode() {
        return makerCode;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public boolean costMoreThan(int moneyInCents) {
        return priceInCents>moneyInCents;
    }
}
