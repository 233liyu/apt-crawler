package main.database.dbInterface;

import main.Beans.Data;
import main.Beans.Tag;

import java.sql.SQLException;
import java.util.List;

/**
 * 用于数据清洗对Tag的编写, 提供面向Tag进行编辑
 * */

public interface DataTagInterface {

    public List<Tag> getTagsOfData(Data data) throws SQLException;

    public void addTagToData(Data data, Tag tag) throws Exception;

    public void removeTagData(Data data, Tag tag) throws Exception;

    public void createTag(Tag tag) throws Exception;

    public Tag findByID(int id) throws SQLException;

    public Tag findByName(String name);

    public List<Tag> getAllTags();

    public List<Data> getDataByTag(Tag tag);
}
