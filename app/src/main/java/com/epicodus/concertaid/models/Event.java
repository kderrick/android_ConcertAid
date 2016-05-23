package com.epicodus.concertaid.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Event {
    String eventTitle;
    String eventFacebookRSVP;
    String eventArtist;
    String eventVenue;
    String eventDate;
    String eventArtistImage;
    String eventArtistURL;
    String pushId;
    String index;

    public Event() {};


    public Event(String title, String facebookRSVP, String eventArtistImage) {

//        , String artist, String venue, double latitude, double  longitude, String date, String artistImage, String artistURL

        this.eventTitle = title;
        this.eventFacebookRSVP = facebookRSVP;
        this.eventArtistImage = eventArtistImage;
        this.index = "not_specified";
//        this.eventArtist = artist;
//        this.eventVenue = venue;
//        this.eventLatitude = latitude;
//        this.eventLongitude = longitude;
//        this.eventDate = date;
//        this.eventArtistImage = artistImage;
//        this.eventArtistURL = artistURL;
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventFacebookRSVP() { return eventFacebookRSVP;}

    public String getEventArtistImage() { return eventArtistImage;}


//    public String getEventArtist() {
//        return eventArtist;
//    }
//
//    public String getEventVenue() {
//        return eventVenue;
//    }
//
//    public double getEventLatitude() {
//        return eventLatitude;
//    }
//
//    public double getEventLongitude() {
//        return eventLongitude;
//    }
//
//    public String getEventDate() {
//        return eventDate;
//    }
//
//    public String getEventArtistImage() {
//        return eventArtistImage;
//    }
//    public String getEventArtistURL() {
//        return eventArtistURL;
//    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


}
