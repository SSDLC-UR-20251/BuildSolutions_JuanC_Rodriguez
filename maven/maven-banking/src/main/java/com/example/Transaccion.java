public class Transaccion {
    private String balance;
    private String type;
    private String timestamp;

    public Transaccion(String balance, String type, String timestamp) {
        this.balance = balance;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getBalance() { return balance; }
    public String getType() { return type; }
    public String getTimestamp() { return timestamp; }
}
