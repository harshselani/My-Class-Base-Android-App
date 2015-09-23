package in.knowledgeportal.myclass.ccpt.Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import in.knowledgeportal.myclass.ccpt.Classes.Test;
import in.knowledgeportal.myclass.ccpt.R;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by harsh on 29-05-2015.
 */
public class TestCard extends Card {

    protected TextView topic;
    protected TextView highest;
    protected TextView test_date;
    protected TextView test_name;
    protected TextView average;



    protected Test test;





    public TestCard(Context context, Test test_obj) {
        this(context, R.layout.marks_layout);
        test = test_obj;
    }

    public TestCard(Context context, int innerLayout) {
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
        topic = (TextView) parent.findViewById(R.id.toppers_topic);
        highest = (TextView) parent.findViewById(R.id.toppers_highest);
        test_date = (TextView) parent.findViewById(R.id.toppers_test_date);
        test_name = (TextView) parent.findViewById(R.id.toppers_test_name);
        average = (TextView) parent.findViewById(R.id.toppers_average);

          topic.setText("Topic : "+ test.getTopic());
          highest.setText("Total : "+String.valueOf(test.getMaximum()));
          test_date.setText("Date : " + test.getDate_test());
          test_name.setText(test.getName());
          average.setText("Total : " + String.valueOf(test.getAverage()));


//        //iv.setImageResource(R.drawable.ic_launcher);


    }
}