package com.epicodus.concertaid.models;

import org.parceler.Parcel;



@Parcel
public class Event {
    private String mEventTitle;
    private String mEventFacebookRSVP;
    private String mEventArtist;
    private String mEventVenue;
    private double mEventLatitude;
    private double mEventLongitude;
    private String mEventDate;
    private String mEventArtistImage;
    private String mEventArtistURL;
    private String mEventBuyTickets;

    public Event() {};


    public Event(String title, String facebookRSVP) {

//        , String artist, String venue, double latitude, double  longitude, String date, String artistImage, String artistURL, String eventBuyTickets
        this.mEventTitle = title;
        this.mEventFacebookRSVP = facebookRSVP;
//        this.mEventArtist = artist;
//        this.mEventVenue = venue;
//        this.mEventLatitude = latitude;
//        this.mEventLongitude = longitude;
//        this.mEventDate = date;
//        this.mEventArtistImage = artistImage;
//        this.mEventArtistURL = artistURL;
//        this.mEventBuyTickets = eventBuyTickets;
    }


    public String getEventTitle() {
        return mEventTitle;
    }

    public String getmEventFacebookRSVP() { return mEventFacebookRSVP;}

//    public String getEventArtist() {
//        return mEventArtist;
////    }
//
//    public String getEventVenue() {
//        return mEventVenue;
//    }
//
//    public double getEventLatitude() {
//        return mEventLatitude;
//    }
//
//    public double getEventLongitude() {
//        return mEventLongitude;
//    }
//
//    public String getEventDate() {
//        return mEventDate;
//    }
//
//    public String getEventArtistImage() {
//        return mEventArtistImage;
//    }
//    public String getEventArtistURL() {
//        return mEventArtistURL;
//    }
//
//    public String getEventBuyTickets() {
//        return mEventBuyTickets;
//    }


}
