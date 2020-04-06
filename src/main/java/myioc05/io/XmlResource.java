package myioc05.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class XmlResource implements Resource {
    private String location;

    public XmlResource(String location) {
        this.location = location;
    }


    @Override
    public InputStream getInputStream() {
        try {
            URL resource = this.getClass().getClassLoader().getResource(location);
            assert resource != null;
            URLConnection urlConnection = resource.openConnection();
            return urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
