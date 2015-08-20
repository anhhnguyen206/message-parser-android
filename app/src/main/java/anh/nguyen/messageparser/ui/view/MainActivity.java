package anh.nguyen.messageparser.ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import anh.nguyen.messageparser.R;
import anh.nguyen.messageparser.common.base.InjectableActivity;
import anh.nguyen.messageparser.model.MessageMetadata;

public class MainActivity extends InjectableActivity implements MainView {

    @Override
    protected List<Object> getModules() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bindMetadata(String message) {

    }

    @Override
    public void bindMetadata(MessageMetadata messageMetadata) {

    }

    @Override
    public void showMetadataAsString() {

    }

    @Override
    public void showMetadataAsCards() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
