package com.chriseconomou.sampleproject.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.database.BagDatabaseAdapter;
import com.chriseconomou.sampleproject.event.AddToBagEvent;
import com.chriseconomou.sampleproject.fragment.NavigationDrawerFragment;

import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Base activity, that holds a toolbar instance
 */
public abstract class BaseToolbarActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.toolbar_text_bag)
    TextView mTextBag;

    private BagDatabaseAdapter mBagDatabaseAdapter;
    private int mBagSize = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBagDatabaseAdapter = new BagDatabaseAdapter(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        updateBagSize();
    }

    //Receive event from EventBus when item has been added to bag
    public void onEvent(AddToBagEvent event) {
        updateBagSize();
    }

    @OnClick(R.id.toolbar_text_bag)
    void clickBag() {
        showDialog();
    }

    private void updateBagSize() {
        mBagSize = mBagDatabaseAdapter.getBagSize();
        mTextBag.setText(String.valueOf(mBagSize));
    }


    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.shopping_bag));
        builder.setMessage(getString(R.string.bag_contains, mBagSize));
        builder.setPositiveButton(getString(android.R.string.ok), null);
        builder.setNegativeButton(getString(R.string.clear_bag), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mBagDatabaseAdapter.clearBag();
                updateBagSize();
            }
        });
        builder.show();
    }
}
