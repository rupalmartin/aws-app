package com.aws.service;

import com.aws.dao.FileInfoDao;
import com.aws.dao.UserDao;
import com.aws.entity.FileInfo;
import com.aws.entity.Login;
import com.aws.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService extends AbstractService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileInfoDao fileInfoDao;

    public List<FileInfo> getAllRecords() throws Exception {
        return fileInfoDao.readAll();
    }

    @Transactional
    public void addRecords(FileInfo fileInfo) throws Exception {
        fileInfoDao.addRecord(fileInfo);
    }

    @Transactional
    public void deleteRecord(FileInfo fileInfo)
            throws Exception {
        fileInfoDao.delete(fileInfo);
    }

    @Transactional
    public void update(FileInfo fileInfo)
            throws Exception {
        fileInfoDao.update(fileInfo);
    }

    @Transactional
    public void register(User user) throws Exception {
        userDao.register(user);
    }

    @Transactional
    public User validateUser(Login login) throws Exception {
        return userDao.validateUser(login);
    }

    @Transactional
    public FileInfo read(FileInfo fileInfo) throws Exception {
        return fileInfoDao.read(fileInfo);
    }

    @Transactional
    public FileInfo readbyUrl(FileInfo fileInfo) throws Exception {
        return fileInfoDao.readbyUrl(fileInfo);
    }




}
