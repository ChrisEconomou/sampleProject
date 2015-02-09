package com.chriseconomou.sampleproject.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.event.AddToBagEvent;
import com.chriseconomou.sampleproject.fragment.NavigationDrawerFragment;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Base activity, that holds a toolbar instance
 */
public abstract class BaseToolbarActivity extends BaseActivity {


    private NavigationDrawerFragment mNavigationDrawerFragment;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.toolbar_text_bag)
    TextView mTextBag;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(AddToBagEvent event) {

        mTextBag.setText("1");
    }

}
