package in.knowledgeportal.myclass.ccpt.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.knowledgeportal.myclass.ccpt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {
    private static final String KEY_TITLE = "title";

    public DemoFragment() {
        // Required empty public constructor
    }

    public static DemoFragment newInstance(String title) {
        DemoFragment f = new DemoFragment();

        Bundle args = new Bundle();

        args.putString(KEY_TITLE, title);
        f.setArguments(args);

        return (f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // don't look at this layout it's just a listView to show how to handle the keyboard
        return inflater.inflate(R.layout.fragment_sample, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context =getActivity().getApplicationContext();

//        ArrayList<Card> cards = new ArrayList<Card>();
//
//        Card card1 = new MarksCard(context);
//        card1.setId("10");
//
//        for (int i =0;i<10;i++)
//            cards.add(card1);
//
//        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(context,cards);
//
//        //Staggered grid view
//        CardRecyclerView mRecyclerView = (CardRecyclerView) getView().findViewById(R.id.carddemo_recyclerview);
//        mRecyclerView.setHasFixedSize(false);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//
//        //Set the empty view
//        if (mRecyclerView != null) {
//            mRecyclerView.setAdapter(mCardArrayAdapter);
//        }

    }
}
