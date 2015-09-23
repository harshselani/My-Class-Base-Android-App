package in.knowledgeportal.myclass.ccpt.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.knowledgeportal.myclass.ccpt.Cards.NotesCard;
import in.knowledgeportal.myclass.ccpt.Classes.Notes;
import in.knowledgeportal.myclass.ccpt.R;
import in.knowledgeportal.myclass.ccpt.Utilities.DBHelper;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

public class NotesFragment extends Fragment {
    private static final String KEY_TITLE = "title";

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance(String title) {
        NotesFragment f = new NotesFragment();

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
        final DBHelper dbhelper = new DBHelper(context);



        ArrayList<Notes> notes = dbhelper.getAllNotes();

        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i < notes.size() ; i++) {
            Notes obj = notes.get(i);
            Card card1 = new NotesCard(context,obj);
            //card1.setId(String.valueOf(i));
            cards.add(card1);

        }




        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(context,cards);

        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView) getView().findViewById(R.id.carddemo_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }

    }
}
