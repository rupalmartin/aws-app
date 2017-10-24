package com.aws.dao;

import com.aws.entity.FileInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileInfoDao extends AbstractDao implements ResultSetExtractor<List<FileInfo>> {

    public void addRecord(FileInfo fileInfo) {
        String sql = "insert into fileinfo(firstname,lastname,createdate,updatedate,description,url) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{fileInfo.getFirstname(), fileInfo.getLastname(), fileInfo.getCreatedate(),
                fileInfo.getUpdatedate(),fileInfo.getDescription(),
                fileInfo.getUrl()});
    }

    public void delete(FileInfo fileInfo) throws Exception {
        jdbcTemplate.update("delete from fileinfo where id = ?",
                new Object[]{fileInfo.getId()});
    }

    public void update(FileInfo fileInfo) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String requiredDate = df.format(new Date());
        jdbcTemplate.update(
                "update fileinfo set updatedate=? where url=?",
                new Object[]{requiredDate, fileInfo.getUrl()});
    }

    public FileInfo read(FileInfo fileInfo) throws Exception {
        String sql = "select * from fileinfo where id=" + fileInfo.getId();
        List<FileInfo> fileI = jdbcTemplate.query(sql, new FileInfoMapper());
        return fileI.size() > 0 ? fileI.get(0) : null;
    }

    public FileInfo readbyUrl(FileInfo fileInfo) throws Exception {
        String sql = "select * from fileinfo where url='" + fileInfo.getUrl() + "'";
        List<FileInfo> fileI = jdbcTemplate.query(sql, new FileInfoMapper());
        return fileI.size() > 0 ? fileI.get(0) : null;
    }

    public List<FileInfo> readAll() throws Exception {
        String sql = "select * from fileinfo";
        return (List<FileInfo>) jdbcTemplate.query(sql, this);
    }


    class FileInfoMapper implements RowMapper<FileInfo> {
        public FileInfo mapRow(ResultSet rs, int arg1) throws SQLException {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(rs.getString("id"));
            fileInfo.setFirstname(rs.getString("firstname"));
            fileInfo.setLastname(rs.getString("lastname"));
            fileInfo.setCreatedate(rs.getString("createdate"));
            fileInfo.setUpdatedate(rs.getString("updatedate"));
            fileInfo.setDescription(rs.getString("description"));
            fileInfo.setUrl(rs.getString("url"));
            return fileInfo;
        }
    }


    @Override
    public List<FileInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
        FileInfo fileInfo = null;
        List<FileInfo> fileInfos = new ArrayList<FileInfo>();
        while (rs.next()) {
            fileInfo = new FileInfo();
            fileInfo.setId(rs.getString("id"));
            fileInfo.setFirstname(rs.getString("firstname"));
            fileInfo.setLastname(rs.getString("lastname"));
            fileInfo.setCreatedate(rs.getString("createdate"));
            fileInfo.setUpdatedate(rs.getString("updatedate"));
            fileInfo.setDescription(rs.getString("description"));
            fileInfo.setUrl(rs.getString("url"));
            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }
}
