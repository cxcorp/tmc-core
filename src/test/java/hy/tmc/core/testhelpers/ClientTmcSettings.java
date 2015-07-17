package hy.tmc.core.testhelpers;

import com.google.common.base.Optional;
import hy.tmc.core.configuration.TmcSettings;
import hy.tmc.core.domain.Course;

public class ClientTmcSettings implements TmcSettings {

    private String serverAddress;
    private String username;
    private String password;
    private Course currentCourse;
    private String apiVersion;
    private String mainDirectory;

    public ClientTmcSettings() {
        apiVersion = "7";
    }

    public ClientTmcSettings(String uname, String pword) {
        this();
        this.username = uname;
        this.password = pword;
    }

    public ClientTmcSettings(String uname, String pword, String url) {
        this(uname, pword);
        this.serverAddress = url;
    }

    public ClientTmcSettings(Course course) {
        this();
        this.currentCourse = course;
    }

    public ClientTmcSettings(String uname, String pword, Course course) {
        this(uname, pword);
        this.currentCourse = course;
    }

    @Override
    public synchronized String getServerAddress() {
        return serverAddress;
    }

    @Override
    public synchronized String getFormattedUserData() {
        return this.username + ":" + this.password;
    }

    @Override
    public synchronized String getPassword() {
        return password;
    }

    @Override
    public synchronized String getUsername() {
        return username;
    }

    @Override
    public synchronized boolean userDataExists() {
        return !(this.username == null || this.password == null || 
                this.username.isEmpty() || this.password.isEmpty());
    }

    @Override
    public synchronized Optional<Course> getCurrentCourse() {
        return Optional.of(currentCourse);
    }

    public synchronized void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }

    public synchronized void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    @Override
    public String apiVersion() {
        return apiVersion;
    }
    
    @Override
    public String toString() {
        return this.password + ":" + this.password + " " + this.serverAddress;
    }

    @Override
    public String getTmcMainDirectory() {
        return this.mainDirectory;
    }

    public void setTmcMainDirectory(String path) {
        this.mainDirectory = path;
    }
}