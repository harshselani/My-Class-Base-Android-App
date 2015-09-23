package in.knowledgeportal.myclass.ccpt.Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import in.knowledgeportal.myclass.ccpt.Classes.Announcement;
import in.knowledgeportal.myclass.ccpt.R;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by harsh on 29-05-2015.
 */
public class ToppersCard extends Card {

    protected TextView name;
    protected TextView date;
    protected Announcement announcement;

    public ToppersCard(Context context, Announcement obj) {
        this(context, R.layout.announcement_layout);
        announcement = obj;

    }

    public ToppersCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        name = (TextView) parent.findViewById(R.id.announcement_name);
        name.setText(announcement.getName());

        date = (TextView) parent.findViewById(R.id.announcement_date);
        date.setText(announcement.getDatecreated());


    }
}