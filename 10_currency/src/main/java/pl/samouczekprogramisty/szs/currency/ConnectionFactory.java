package pl.samouczekprogramisty.szs.currency;

public class ConnectionFactory {
    public HttpConnection build(String url) {
        return new HttpConnection(url);
    }
}
