package in.knowledgeportal.myclass.ccpt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import in.knowledgeportal.myclass.ccpt.Fragment.AnnouncementFragment;
import in.knowledgeportal.myclass.ccpt.Fragment.MarksFragment;
import in.knowledgeportal.myclass.ccpt.Fragment.NotesFragment;
import in.knowledgeportal.myclass.ccpt.Fragment.ToppersFragment;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_dark_toolbar);

        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        //final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460");

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                //.addProfiles(profile)
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Marks").withIcon(GoogleMaterial.Icon.gmd_wb_sunny).withIdentifier(1).withCheckable(false),
                        new PrimaryDrawerItem().withName("Toppers List").withIcon(FontAwesome.Icon.faw_home).withIdentifier(2).withCheckable(false),
                        new PrimaryDrawerItem().withName("Downloads").withIcon(FontAwesome.Icon.faw_bicycle).withIdentifier(3).withCheckable(false),
                        new PrimaryDrawerItem().withName("Announcements").withIcon(FontAwesome.Icon.faw_eye).withIdentifier(4).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(GoogleMaterial.Icon.gmd_style).withIdentifier(6).withCheckable(false)
                        ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;

                             if (drawerItem.getIdentifier() == 1) {

                                //intent = new Intent(MainActivity.this, SimpleFragmentDrawerActivity.class);

                                 Fragment f = MarksFragment.newInstance("Marks");
                                 toolbar.setTitle("Marks");

                                 getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();

                            }

                            if (drawerItem.getIdentifier() == 2) {

                                //intent = new Intent(MainActivity.this, SimpleFragmentDrawerActivity.class);

                                Fragment f = ToppersFragment.newInstance("Toppers List");
                                toolbar.setTitle("Toppers List");

                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();

                            }

                            if (drawerItem.getIdentifier() == 3) {

                                //intent = new Intent(MainActivity.this, SimpleFragmentDrawerActivity.class);

                                Fragment f = NotesFragment.newInstance("Notes");
                                toolbar.setTitle("Notes");

                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();

                            }

                            if (drawerItem.getIdentifier() == 4) {

                                //intent = new Intent(MainActivity.this, SimpleFragmentDrawerActivity.class);

                                Fragment f = AnnouncementFragment.newInstance("Announcements");
                                toolbar.setTitle("Announcements");
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();

                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();


        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 10
            result.setSelectionByIdentifier(1, false);

            Fragment f = MarksFragment.newInstance("Marks");
            toolbar.setTitle("Marks");

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();


            //set the active profile
            //headerResult.setActiveProfile(profile);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}