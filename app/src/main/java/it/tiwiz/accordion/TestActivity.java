package it.tiwiz.accordion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.tiwiz.accordion.library.AccordionLayout;


public class TestActivity extends ActionBarActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AccordionLayout accordionLayout = (AccordionLayout) findViewById(R.id.accordion2);
        accordionLayout.setHeaderLayout(R.layout.test_header);
    }

}
