package com.example.codecircuits_final;

public class MainClass {
    private String mContestName;
    private String mEndTime;
    private String mPlatform;

    public MainClass(String mContestName, String mEndTime, String mPlatform) {
        this.mContestName = mContestName;
        this.mEndTime = mEndTime;
        this.mPlatform = mPlatform;
    }

    public String getmContestName(){
        return mContestName;
    }
    public String getmEndTime(){
        return mEndTime;
    }
    public String getmPlatform(){
        return mPlatform;
    }
}
