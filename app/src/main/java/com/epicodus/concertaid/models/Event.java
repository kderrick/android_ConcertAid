package com.epicodus.concertaid.models;

import org.parceler.Parcel;



@Parcel
public class Event {
    private String eventTitle;
    private String eventFacebookRSVP;
    private String eventArtist;
    private String eventVenue;
    private double eventLatitude;
    private double eventLongitude;
    private String eventDate;
    private String eventArtistImage;
    private String eventArtistURL;
    private String pushId;

    public Event() {};


    public Event(String title, String facebookRSVP) {

//        , String artist, String venue, double latitude, double  longitude, String date, String artistImage, String artistURL

        this.eventTitle = title;
        this.eventFacebookRSVP = facebookRSVP;
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


}
