package itspay.br.com.model;

/**
 * Created by yesus on 30/12/16.
 */

public class Image {
    private String authority;
    private Content content;
    private long defaultPort;
    private String file;
    private String host;
    private String path;
    private long port;
    private String protocol;
    private String query;
    private String ref;
    private String userInfo;

    public Image(String authority, Content content, long defaultPort, String file, String host, String path, long port, String protocol, String query, String ref, String userInfo){
        this.authority = authority;
        this.content = content;
        this.defaultPort = defaultPort;
        this.file = file;
        this.host = host;
        this.path = path;
        this.port = port;
        this.protocol = protocol;
        this.query = query;
        this.ref = ref;
        this.userInfo = userInfo;
    }

    public Image() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public long getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(long defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
