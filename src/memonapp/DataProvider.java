
package memonapp;


public class DataProvider {

    static DataProvider handler = null;
    String user;
    String listTitle;

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static DataProvider getInstance() throws Exception {
        if (handler == null) {
            handler = new DataProvider();
        }
        return handler;
    }
}
