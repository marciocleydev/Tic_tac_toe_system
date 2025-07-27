package domain;

import domain.domainEnums.Type;

public class Player {
    private String name;
    private Type playertype;

    public Player(String name, Type playertype) {
        this.name = name;
        this.playertype = playertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getPlayertype() {
        return playertype;
    }

    public void setPlayertype(Type playertype) {
        this.playertype = playertype;
    }

    @Override
    public String toString() {
        return String.format(" %-10s (%s)", getName(), getPlayertype());
    }

}
