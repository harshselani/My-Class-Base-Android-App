package in.knowledgeportal.myclass.ccpt.Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import in.knowledgeportal.myclass.ccpt.Classes.Notes;
import in.knowledgeportal.myclass.ccpt.R;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by harsh on 29-05-2015.
 */
public class NotesCard extends Card {

    protected TextView name;
    protected TextView date;
    protected Notes notes;

    public NotesCard(Context context, Notes obj) {
        this(context, R.layout.announcement_layout);
        notes = obj;

    }

    public NotesCard(Context context, int innerLayout) {
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
        name = (TextView) parent.findViewById(R.id.notes_name);
        name.setText(notes.getName());

        date = (TextView) parent.findViewById(R.id.notes_date);
        date.setText(notes.getDatecreated());


    }
}