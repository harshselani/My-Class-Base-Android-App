package in.knowledgeportal.myclass.ccpt.Cards;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import in.knowledgeportal.myclass.ccpt.Classes.Marks;
import in.knowledgeportal.myclass.ccpt.R;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by harsh on 29-05-2015.
 */
public class MarksCard extends Card {

    protected TextView topic;
    protected TextView highest;
    protected TextView test_date;
    protected TextView test_name;
    protected ImageView iv;
    protected TextDrawable drawable2;


    protected Marks mark;





    public MarksCard(Context context, Marks mark_obj) {
        this(context, R.layout.marks_layout);
        mark = mark_obj;
    }

    public MarksCard(Context context, int innerLayout) {
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
        topic = (TextView) parent.findViewById(R.id.topic);
        highest = (TextView) parent.findViewById(R.id.highest);
        test_date = (TextView) parent.findViewById(R.id.test_date);
        test_name = (TextView) parent.findViewById(R.id.test_name);
        iv = (ImageView) parent.findViewById(R.id.imageView2);

        Integer avg = mark.getAverage();
        Integer score = mark.getScore();
        Integer color = Color.RED;

        if(score>avg)
        {
            color = Color.GREEN;
        }

        if(score<avg)
        {
            color = Color.RED;
        }

        if(score==avg)
        {
            color = Color.YELLOW;
        }




        drawable2 = TextDrawable.builder().buildRound(String.valueOf(mark.getScore()),color);

        //mRatingBar = (RatingBar) parent.findViewById(R.id.carddemo_myapps_main_inner_ratingBar);
//
//        topic.setText("Very knsldnkgl ksndgklnsd ksndglknsd snldkgnskdglk sdg");
//        highest.setText("Max : 36/360");
//        test_date.setText("Date : 26/5/15");
//        test_name.setText("Max : 36/360");



          topic.setText("Topic : "+mark.getTopic());
          highest.setText("Highest : "+String.valueOf(mark.getHighest())+"/"+String.valueOf(mark.getTotal()));
          test_date.setText("Date : "+mark.getDate_test());
          test_name.setText(mark.getTest_name());


         iv.setImageDrawable(drawable2);
//        //iv.setImageResource(R.drawable.ic_launcher);


    }
}