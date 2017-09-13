package main.database.dbInterface;

import java.sql.SQLException;

public interface countDATA {
    public int return_apt() throws SQLException;

    public int return_tag() throws SQLException;

    public int return_crawl() throws SQLException;

    public int retern_todayratio() throws SQLException;
}
