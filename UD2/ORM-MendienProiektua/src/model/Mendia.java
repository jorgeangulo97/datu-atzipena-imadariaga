package model;

public class Mendia {
    int id;
	String izena;
	int altuera;
	String probintzia;

    public Mendia() { }
    
    public Mendia(int id,String izena, int altuera,String probintzia) {
    	this.id = id;
        this.izena = izena;
        this.altuera = altuera;
        this.probintzia = probintzia;
    }
    
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

	public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    
    public int getAltuera() {
        return altuera;
    }

    public void setAltuera(int altuera) {
        this.altuera = altuera;
    }       

    public String getProbintzia() {
		return probintzia;
	}

	public void setProbintzia(String probintzia) {
		this.probintzia = probintzia;
	}

	@Override
    public String toString() {
        return "Mendia{" + "izena=" + izena + ", altuera=" + altuera + '}';
    }
}
