/**
 * Created by fj on 23.06.16.
 */
public enum GameMode {
    zocker("Zocker Variante"),
    safety("SicherheitsVariante");

    String value;

    GameMode(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
    @Override
    public String toString(){
        return value;
    }
}
