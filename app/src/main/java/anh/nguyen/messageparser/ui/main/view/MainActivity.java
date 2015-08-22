package anh.nguyen.messageparser.ui.main.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import anh.nguyen.messageparser.R;
import anh.nguyen.messageparser.common.base.InjectableActivity;
import anh.nguyen.messageparser.di.MainActivityModule;
import anh.nguyen.messageparser.model.MessageMetadataItem;
import anh.nguyen.messageparser.ui.main.presenter.MainPresenter;
import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends InjectableActivity implements MainView {
    @Bind(R.id.recycler_view_metadata)
    RecyclerView mRecyclerViewMetadata;
    @Bind(R.id.scroll_view_metadata_as_json)
    ScrollView mScrollViewMetadataAsJson;
    @Bind(R.id.text_view_metadata)
    TextView mTextViewMetadata;
    @Bind(R.id.text_view_message)
    TextView mTextViewMessage;

    @OnClick(R.id.button_parse)
    public void parse() {
        String message = mTextViewMessage.getText().toString();
        mMainPresenter.parse(message);
    }

    ProgressDialog mProgressDialog;
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected List<Object> getModules() {
        return Arrays.asList((Object) new MainActivityModule(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Processing...");
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewMetadata.setLayoutManager(mLinearLayoutManager);
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

        switch (id) {
            case R.id.action_view_as_list:
                showMetadataAsList();
                return true;
            case R.id.action_view_as_string:
                showMetadataAsString();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void bindMetadata(String message) {
        mTextViewMetadata.setText(message);
    }

    @Override
    public void bindMetadata(List<MessageMetadataItem> messageMetadataItems) {
        MetadataAdapter metadataAdapter = new MetadataAdapter(messageMetadataItems);
        mRecyclerViewMetadata.setAdapter(metadataAdapter);
        metadataAdapter.notifyDataSetChanged();
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
