package anh.nguyen.messageparser.ui.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

import anh.nguyen.messageparser.R;
import anh.nguyen.messageparser.common.base.InjectableActivity;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.model.MessageMetadataItem;
import butterknife.Bind;

public class MainActivity extends InjectableActivity implements MainView {
    @Bind(R.id.recycler_view_metadata)
    RecyclerView mRecyclerViewMetadata;
    @Bind(R.id.scroll_view_metadata_as_json)
    ScrollView mScrollViewMetadataAsJson;
    @Bind(R.id.text_view_metadata)
    AppCompatTextView mAppCompatTextViewMetadata;

    ProgressDialog mProgressDialog;

    @Override
    protected List<Object> getModules() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Processing...");
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
        mAppCompatTextViewMetadata.setText(message);
    }

    @Override
    public void bindMetadata(List<MessageMetadataItem> messageMetadatas) {

    }

    @Override
    public void showMetadataAsString() {
        mRecyclerViewMetadata.setVisibility(View.GONE);
        mScrollViewMetadataAsJson.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMetadataAsList() {
        mScrollViewMetadataAsJson.setVisibility(View.GONE);
        mRecyclerViewMetadata.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }
}
