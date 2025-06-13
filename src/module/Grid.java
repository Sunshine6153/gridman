package moudle;

import java.io.Serializable;

public class Grid implements Serializable {
    private String user;
    private String grid;
    private String site;
    private String level;
    private String information;

    public String getUser() {

        return user;
    }
    public String getGrid() {

        return grid;
    }

    public String getSite() {

        return site;
    }

    public String getLevel(){
        return level;
    }

    public String getInformation(){
        return information;
    }

    public Grid(String user, String grid, String site,String level,String information) {
        this.user = user;
        this.grid = grid;
        this.site = site;
        this.level= level;
        this.information= information;
    }
}

