package com.android.benjaminclarke.tetra;

import android.content.Context;
import android.test.ActivityTestCase;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.SharedPreferences;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CardChooserTest {
// TODO: Can't get these mocks to work correctly
    @Mock
    Context mMockContext;
    private CardChooser cardChooser;
    private CardHolder cardHolder;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void onSingleTapConfirmed() throws Exception {

    }

}