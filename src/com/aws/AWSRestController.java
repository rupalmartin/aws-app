package com.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.apigateway.model.Model;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.EC2MetadataUtils;
import com.aws.entity.FileInfo;
import com.aws.entity.Login;
import com.aws.entity.User;
import com.aws.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author rmartin
 */

@RestController
public class AWSRestController {

    private static final Logger logger = LoggerFactory
            .getLogger(AWSRestController.class);
    private static String AWS_ACCESS_KEY_ID = "aws-access-key";
    private static String AWS_SECRET_ACCESS_KEY = "aws-secret-key";
    private static String accessKey = null;
    private static String secretKey = null;
    private static String fname = null;
    private static String lname = null;

    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "users/readbulk", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    public List<FileInfo> getAllRecords(FileInfo fileInfo, Locale locale, Model model) {
        try {
            return adminService.getAllRecords();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "users/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteRecord(FileInfo fileInfo, Locale locale, Model model) {

        try {
            FileInfo fileObj = adminService.read(fileInfo);
            adminService.deleteRecord(fileInfo);
            if (fileObj != null) {
                String[] sub = fileObj.getUrl().split("/");
                deleteFromS3(sub[sub.length - 1]);
            }
        } catch (Exception e) {
            return "User Record delete failed: " + e.getMessage();
        }

        return "User Record delete succesfully!";
    }

    @RequestMapping(value = "register", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    public ModelAndView register(@ModelAttribute("user") User user, Locale locale, Model model) throws Exception {
        try {
            adminService.register(user);
            setFname(user.getFirstname());
            setLname(user.getLastname());
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return new ModelAndView("read", "username", user.getUsername());
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST}, produces = "application/json")
    public ModelAndView login(@ModelAttribute("login") Login login, Locale locale, Model model) throws Exception {
        try {
            User user = adminService.validateUser(login);
            if (user != null) {
                setFname(user.getFirstname());
                setLname(user.getLastname());
                return new ModelAndView("read", "username", user.getUsername());
            } else {
                return new ModelAndView("index", "message", "Username doesn't exist or invalid credentials !!!");
            }
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return new ModelAndView("index", "message", "Username doesn't exist or invalid credentials !!!");
    }

    @RequestMapping(value = "upload", method = {RequestMethod.POST})
    public ModelAndView uploadToS3(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            String url = "https://s3.amazonaws.com/aws-app-281/" + name;
            FileInfo fobj = new FileInfo();
            fobj.setUrl(url);
            FileInfo fileObj = adminService.readbyUrl(fobj);
            boolean result = writeToS3(name, file);
            if (result) {
                if (fileObj == null) {
                    if (getFname() != null) {
                        String createDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                        FileInfo fileInfo = new FileInfo(getFname(), getLname(), name, url);
                        fileInfo.setCreatedate(createDate);
                        fileInfo.setUpdatedate(createDate);
                        adminService.addRecords(fileInfo);
                    }
                } else {
                    adminService.update(fobj);
                }
            }
            return new ModelAndView("read");
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ModelAndView("read");
    }


    private boolean writeToS3(String name, MultipartFile file) {
        String bucketName = "aws-app-281";
        AmazonS3 s3client;
        getCredentials();
        try {
            if (getAccessKey() != null) {
                s3client = AmazonS3ClientBuilder.standard()
                        .build();
            } else {
                BasicAWSCredentials awsCreds = new BasicAWSCredentials(AWS_ACCESS_KEY_ID,
                        AWS_SECRET_ACCESS_KEY);
                s3client = AmazonS3ClientBuilder.standard()
                        .withRegion("us-east-1")
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .build();
            }
            InputStream obj = file.getInputStream();
            System.out.println("Uploading a new file to S3......" + bucketName + "/" + name);
            s3client.putObject(new PutObjectRequest(
                    bucketName, name, obj, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
            return true;
        } catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            logger.error("Error Message:    " + ase.getMessage());
            logger.error("AWS Error Code:   " + ase.getErrorCode());
        } catch (AmazonClientException ace) {
            logger.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            logger.error("Error Message:    " + ace.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void deleteFromS3(String name) {
        String bucketName = "aws-app-281";
        AmazonS3 s3client;
        getCredentials();
        try {
            if (getAccessKey() != null) {
                s3client = AmazonS3ClientBuilder.standard()
                        .build();
            } else {
                BasicAWSCredentials awsCreds = new BasicAWSCredentials(AWS_ACCESS_KEY_ID,
                        AWS_SECRET_ACCESS_KEY);
                s3client = AmazonS3ClientBuilder.standard()
                        .withRegion("us-east-1")
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .build();
            }

            System.out.println("Deleting file from S3......" + bucketName + "/" + name);
            s3client.deleteObject(new DeleteObjectRequest(bucketName, name));
        } catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            logger.error("Error Message:    " + ase.getMessage());
            logger.error("AWS Error Code:   " + ase.getErrorCode());
        } catch (AmazonClientException ace) {
            logger.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            logger.error("Error Message:    " + ace.getMessage());
        }
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static void setAccessKey(String accessKey) {
        AWSRestController.accessKey = accessKey;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secretKey) {
        AWSRestController.secretKey = secretKey;
    }

    private void getCredentials() {
        try {
            Map<String, EC2MetadataUtils.IAMSecurityCredential> credMap = EC2MetadataUtils.getIAMSecurityCredentials();
            Iterator<Map.Entry<String, EC2MetadataUtils.IAMSecurityCredential>> it = credMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, EC2MetadataUtils.IAMSecurityCredential> pair = it.next();
                EC2MetadataUtils.IAMSecurityCredential cred = pair.getValue();
                setAccessKey(cred.accessKeyId);
                setSecretKey(cred.secretAccessKey);
            }
        } catch (Exception e) {
            System.out.println("Unable to contact EC2 metadata service");
        }
    }

    public static String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        AWSRestController.fname = fname;
    }

    public static String getLname() {
        return lname;
    }

    public static void setLname(String lname) {
        AWSRestController.lname = lname;
    }

}
