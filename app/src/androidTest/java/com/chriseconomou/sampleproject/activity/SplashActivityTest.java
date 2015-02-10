package com.chriseconomou.sampleproject.activity;


import com.chriseconomou.sampleproject.application.SampleApplication;
import com.chriseconomou.sampleproject.network.Api;
import com.chriseconomou.sampleproject.network.controllers.GetCategoriesMenListener;
import com.chriseconomou.sampleproject.network.controllers.GetCategoriesWomenListener;
import com.chriseconomou.sampleproject.util.MockDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.Assert.assertNotNull;


public class SplashActivityTest extends RobolectricActivityTest {



    @Mock
    private Api mApi;

    @Captor
    private ArgumentCaptor<GetCategoriesMenListener> mGetCategoriesMenListener;

    @Captor
    private ArgumentCaptor<GetCategoriesWomenListener> mGetCategoriesWomenListener;

    @Before
    public void setUp() {
        super.setUp(SplashActivity.class);
    }

    @Override
    protected void obtainViewReferences() {

    }

    @Override
    protected void mockObjects(SampleApplication sampleApplication) {
        sampleApplication.setApi(mApi);
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(mActivity);
    }



    @Test
    public void getCategories_success_shouldStoreCategories() {
        getCategoriesMen();
        onCategoriesMenSuccess();
    }


    private void getCategoriesMen() {

        Mockito.verify(mApi).getCategoriesMen(mGetCategoriesMenListener.capture());
    }

    private void getCategoriesWomen() {

        Mockito.verify(mApi).getCategoriesWomen(mGetCategoriesWomenListener.capture());
    }

    private void onCategoriesMenSuccess() {

        mGetCategoriesMenListener.getValue().onGetCategoriesMenSuccesful(MockDataUtils.getCategoriesMenResponse(mActivity));
    }

    private void onCategoriesMenFailed() {
        mGetCategoriesMenListener.getValue().onGetCategoriesMenError(null);
    }

    private void onCategoriesWomenSuccess() {

        mGetCategoriesWomenListener.getValue().onGetCategoriesWomenSuccesful(MockDataUtils.getCategoriesWomenResponse(mActivity));
    }

    private void onCategoriesWomenFailed() {

        mGetCategoriesWomenListener.getValue().onGetCategoriesWomenError(null);
    }


}