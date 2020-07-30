package br.com.horizon.model;

public class Product {

    private int id;
    private String name;
    private int qtd;

    public Product(int id, String name, int qtd) {
        setId(id);
        setName(name);
        setQtd(qtd);
    }

    @Override
    public String toString() {
        return getId() + "/" + getName() + "/" + getQtd();
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
