package com.epicodus.concertaid.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.Event;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.eventImageView) ImageView mEventImageView;
    @Bind(R.id.eventNameTextView) TextView mEventNameTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.dateTextView) TextView mDateTextView;
    @Bind(R.id.venueTextView) TextView mVenueTextView;
    @Bind(R.id.artistWebsiteTextView) TextView mArtistWebsiteTextView;


    @Bind(R.id.saveEventButton) Button mSaveEventButton;
    private SharedPreferences mSharedPreferences;
    private Event mEvent;
    private String mSource;




    public static EventDetailFragment newInstance(Event event, String source) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        args.putString(Constants.KEY_SOURCE, source);
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this,view);

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSaveEventButton.setVisibility(View.GONE);
        } else {
            mSaveEventButton.setOnClickListener(this);
        }

        mWebsiteTextView.setOnClickListener(this);
        mVenueTextView.setOnClickListener(this);
        mArtistWebsiteTextView.setOnClickListener(this);
        mEventNameTextView.setText(mEvent.getEventTitle());
        mWebsiteTextView.setText("Click here to Get Tickets");
        mDateTextView.setText("Date: " + mEvent.getEventDate());
        mVenueTextView.setText("Google Maps : " + mEvent.getEventVenueName());
        mArtistWebsiteTextView.setText("Click here for " + mEvent.getEventArtist() + " website");

        Picasso.with(view.getContext()).load(mEvent.getEventArtistImage()).into(mEventImageView);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mEvent.getEventFacebookRSVP()));
            startActivity(webIntent);
        }

        if (v == mVenueTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@" + mEvent                          .getEventLatitude() + "," + mEvent.getEventLongitude() + ",20z"));
            startActivity(webIntent);
        }

        if (v == mArtistWebsiteTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mEvent.getEventArtistWebsite()));
            startActivity(webIntent);
        }
        if (v == mSaveEventButton) {
            String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
            Firebase userEventsFirebaseRef = new Firebase(Constants.FIREBASE_URL_EVENTS).child(userUid);
            Firebase pushRef = userEventsFirebaseRef.push();
            String eventPushId = pushRef.getKey();
            mEvent.setPushId(eventPushId);
            pushRef.setValue(mEvent);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
         }
    }
}
