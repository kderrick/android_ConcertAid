package com.epicodus.concertaid.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Event {
    String eventTitle;
    String eventFacebookRSVP;
    String eventArtistImage;
    String eventArtist;
    String eventArtistFacebook;
    String eventTicketURL;
    String eventDate;
    String eventLocation;
    String eventArtistWebsite;
    String eventVenueName;
    double eventLongitude;
    double eventLatitude;
    String pushId;
    String index;


    public Event() {};


    public Event(String title, String facebookRSVP, String eventArtistImage, String artistName, String artistFacebook, String ticketURL, String formattedDate, String formattedLocation, String artistWebsite, String venueName, double venueLongitude, double venueLatitude) {


        this.eventTitle = title;
        this.eventFacebookRSVP = facebookRSVP;
        this.eventArtistImage = eventArtistImage;
        this.eventArtist = artistName;
        this.eventArtistFacebook = artistFacebook;
        this.eventTicketURL = ticketURL;
        this.eventDate = formattedDate;
        this.eventLocation = formattedLocation;
        this.eventArtistWebsite = artistWebsite;
        this.eventVenueName = venueName;
        this.eventLongitude = venueLongitude;
        this.eventLatitude = venueLatitude;
        this.index = "not_specified";
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventFacebookRSVP() { return eventFacebookRSVP;}

    public String getEventArtistImage() { return eventArtistImage;}


    public String getEventArtist() {
        return eventArtist;
    }

    public String getEventArtistFacebook() {
        return eventArtistFacebook;
    }

    public String getEventTicketURL() {
        return eventTicketURL;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventArtistWebsite() {
        return eventArtistWebsite;
    }

    public String getEventVenueName() {
        return eventVenueName;
    }

    public double getEventLatitude() {
        return eventLatitude;
    }

    public double getEventLongitude() {
        return eventLongitude;
    }

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
