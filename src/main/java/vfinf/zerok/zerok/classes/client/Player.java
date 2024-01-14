package vfinf.zerok.zerok.classes.client;

public class Player {
    private String nazione;
    private double money;

    public Player(String nazione, double money) {
        this.nazione = nazione;
        this.money = money;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}