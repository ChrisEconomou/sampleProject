package  com.chriseconomou.sampleproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.chriseconomou.sampleproject.application.SampleApplication;
import com.chriseconomou.sampleproject.local.PreferencesStorage;
import com.chriseconomou.sampleproject.util.Utils;

import butterknife.ButterKnife;

/**
 * Base activity, that holds access to global instances and has activity/fragment management methods.
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected PreferencesStorage mPreferenceStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.inject(this);
        SampleApplication app = ((SampleApplication) getApplication());
        mPreferenceStorage = app.getPreferencesStorage();
    }

    protected abstract int getLayoutId();

    protected abstract void initializeViews(Bundle savedInstanceState);

    protected void startActivity(Class classOfActivity) {
        startActivity(classOfActivity, null, false);
    }

    protected void startActivity(Class classOfActivity, boolean newTask) {
        startActivity(classOfActivity, null, newTask);
    }

    protected void startActivity(Class classOfActivity, Bundle bundle) {
        startActivity(classOfActivity, bundle, false);
    }

    protected void startActivity(Class classOfActivity, Bundle bundle, boolean newTask) {
        Intent intent = new Intent(this, classOfActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (newTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    protected void addFragment(Fragment fragment, int containerId, String fragmentTag) {
        Utils.replaceFragment(this, containerId, fragment, fragmentTag, true);
    }

}

