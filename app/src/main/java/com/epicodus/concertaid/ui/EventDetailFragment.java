package com.epicodus.concertaid.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.Event;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.eventImageView) ImageView mEventImageView;
    @Bind(R.id.eventNameTextView) TextView mEventNameTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.phoneTextView) TextView mPhoneTextView;
    @Bind(R.id.addressTextView) TextView mAddressTextView;
    @Bind(R.id.saveEventButton) Button mSaveEventButton;

    private Event mEvent;



    public static EventDetailFragment newInstance(Event event) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this,view);

        mSaveEventButton.setOnClickListener(this);
        mWebsiteTextView.setOnClickListener(this);
        mEventNameTextView.setText(mEvent.getEventTitle());
        mWebsiteTextView.setText(mEvent.getEventFacebookRSVP());
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mEvent.getEventFacebookRSVP()));
            startActivity(webIntent);
        }
        if (v == mSaveEventButton) {
            Firebase ref = new Firebase(Constants.FIREBASE_URL_EVENTS);
            ref.push().setValue(mEvent);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
         }
    }
}
