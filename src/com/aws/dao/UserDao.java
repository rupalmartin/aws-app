package com.aws.dao;

import com.aws.entity.Login;
import com.aws.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao implements ResultSetExtractor<List<User>> {

    public void register(User user) {
        String sql = "insert into users values(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getFirstname(),
                user.getLastname()});
    }

    public User validateUser(Login login) {
        String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
                + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }


    class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int arg1) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            return user;
        }
    }

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = null;
        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        return users;
    }


}
