package module;

import java.io.Serializable;

public class GridDeliver implements Serializable {
    private String Site;
    private String So2con;
    private String Cocon;
    private String Pm25con;
    private String So2level;
    private String Colevel;
    private String Pm25level;
    private String AQlevel;

    public GridDeliver(String site, String so2con, String cocon, String pm25con, String so2level, String colevel, String pm25level, String AQlevel) {
        this.Site = site;
        this.So2con = so2con;
        this.Cocon = cocon;
        this.Pm25con = pm25con;
        this.So2level = so2level;
        this.Colevel = colevel;
        this.Pm25level = pm25level;
        this.AQlevel = AQlevel;
    }
    public String getSite(){
        return Site;
    }
}

