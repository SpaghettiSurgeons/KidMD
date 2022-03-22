package com.example.kidmd;

public class ProgressTrack {
    public String bp;
    public String hr;
    public String pr;
    public String to;
    public String uid;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBp() {
        return bp;
    }

    public String getHr() {
        return hr;
    }

    public String getPr() {
        return pr;
    }

    public String getTo() {
        return to;
    }


    public ProgressTrack() {
        this.bp = "";
        this.hr = "";
        this.pr = "";
        this.to = "";
    }

    public ProgressTrack(String bp, String hr, String pr, String to) {
        this.bp = bp;
        this.hr = hr;
        this.pr = pr;
        this.to = to;
    }

}
