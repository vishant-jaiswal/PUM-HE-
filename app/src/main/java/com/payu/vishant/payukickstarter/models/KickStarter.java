package com.payu.vishant.payukickstarter.models;

import com.payu.vishant.payukickstarter.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Vishant on 8/13/2017.
 */

public class KickStarter implements Serializable {
    private int s_no;
    private long amt_pledged;
    private String blurb;
    private String by;
    private String country;
    private String currency;
    private Date end_time;
    private String location;
    private int percentage_funded;
    private String num_backers;
    private String state;
    private String title;
    private String type;
    private String url;


    public KickStarter() {
    }

    public KickStarter(JSONObject j) {

        try {
            if (j.has("s.no")) {
                this.s_no = j.getInt("s.no");
            } else {
                this.s_no = 0;
            }

            if (j.has("amt.pledged")) {
                this.amt_pledged = j.getInt("amt.pledged");
            } else {
                this.amt_pledged = 0;
            }

            if (j.has("blurb")) {
                this.blurb = j.getString("blurb");
            } else {
                this.blurb = "";
            }

            if (j.has("by")) {
                this.by = j.getString("by");
            } else {
                this.by = "";
            }

            if (j.has("country")) {
                this.country = j.getString("country");
            } else {
                this.country = "";
            }

            if (j.has("currency")) {
                this.currency = j.getString("currency");
            } else {
                this.currency = "";
            }

            if (j.has("end.time")) {
                this.end_time = Utils.getDateFromString(j.getString("end.time"));
            } else {
                this.end_time = new Date();
            }

            if (j.has("location")) {
                this.location = j.getString("location");
            } else {
                this.location = "";
            }

            if (j.has("percentage.funded")) {
                this.percentage_funded = j.getInt("percentage.funded");
            } else {
                this.percentage_funded = 0;
            }

            if (j.has("num.backers")) {
                this.num_backers = j.getString("num.backers");
            } else {
                this.num_backers = "";
            }

            if (j.has("state")) {
                this.state = j.getString("state");
            } else {
                this.state = "";
            }

            if (j.has("title")) {
                this.title = j.getString("title");
            } else {
                this.title = "";
            }

            if (j.has("type")) {
                this.type = j.getString("type");
            } else {
                this.type = "";
            }

            if (j.has("url")) {
                this.url = j.getString("url");
            } else {
                this.url = "";
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getS_no() {
        return s_no;
    }

    public void setS_no(int s_no) {
        this.s_no = s_no;
    }

    public long getAmt_pledged() {
        return amt_pledged;
    }

    public void setAmt_pledged(long amt_pledged) {
        this.amt_pledged = amt_pledged;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPercentage_funded() {
        return percentage_funded;
    }

    public void setPercentage_funded(int percentage_funded) {
        this.percentage_funded = percentage_funded;
    }

    public String getNum_backers() {
        return num_backers;
    }

    public void setNum_backers(String num_backers) {
        this.num_backers = num_backers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class TimeComparatorASC implements Comparator<KickStarter> {

        @Override
        public int compare(KickStarter uc1, KickStarter uc2) {
            if (uc1.getEnd_time().after(uc2.getEnd_time())) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static class TimeComparatorDSC implements Comparator<KickStarter> {

        @Override
        public int compare(KickStarter uc1, KickStarter uc2) {
            if (uc2.getEnd_time().after(uc1.getEnd_time())) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static class TitleComaparatorASC implements Comparator<KickStarter> {

        @Override
        public int compare(KickStarter kickStarter, KickStarter t1) {
            return kickStarter.getTitle().compareToIgnoreCase(t1.getTitle());
        }
    }

    public static class TitleComaparatorDSC implements Comparator<KickStarter> {

        @Override
        public int compare(KickStarter kickStarter, KickStarter t1) {
            return t1.getTitle().compareToIgnoreCase(kickStarter.getTitle());
        }
    }
}
