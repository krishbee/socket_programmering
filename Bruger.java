package sock;

public class Bruger {

    private String navn;
    private String ip;

    public Bruger(String navn, String ip){
        this.navn = navn;
        this.ip = ip;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
