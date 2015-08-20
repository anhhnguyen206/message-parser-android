package anh.nguyen.messageparser.ui.view;

import android.os.Environment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import anh.nguyen.messageparser.R;
import anh.nguyen.messageparser.model.EmoticonItem;
import anh.nguyen.messageparser.model.HeaderItem;
import anh.nguyen.messageparser.model.LinkItem;
import anh.nguyen.messageparser.model.MentionItem;
import anh.nguyen.messageparser.model.MessageMetadataItem;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class MetadataAdapter extends RecyclerView.Adapter<MetadataAdapter.ViewHolder> {
    private List<MessageMetadataItem> mMessageMetadataItems;

    public MetadataAdapter(List<MessageMetadataItem> messageMetadataItems) {
        mMessageMetadataItems = messageMetadataItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        MessageMetadataItem messageMetadataItem = mMessageMetadataItems.get(position);
        View view = null;
        switch (messageMetadataItem.getType()) {
            case MessageMetadataItem.HEADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header, viewGroup, false);
                break;
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_metadata, viewGroup, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        MessageMetadataItem messageMetadataItem = mMessageMetadataItems.get(position);

        String value = "";

        switch (messageMetadataItem.getType()) {
            case MessageMetadataItem.HEADER:
                HeaderItem headerItem = (HeaderItem) messageMetadataItem;
                value = headerItem.getValue();
                break;
            case MessageMetadataItem.MENTION:
                MentionItem mentionItem = (MentionItem) messageMetadataItem;
                value = mentionItem.getValue();
                break;
            case MessageMetadataItem.EMOTICON:
                EmoticonItem emoticonItem = (EmoticonItem) messageMetadataItem;
                value = emoticonItem.getValue();
                break;
            case MessageMetadataItem.LINK:
                LinkItem linkItem = (LinkItem) messageMetadataItem;
                value = linkItem.getValue().getTitle() + "\n" + linkItem.getValue().getUrl();
        }

        viewHolder.bind(value);
    }

    @Override
    public int getItemCount() {
        return mMessageMetadataItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content)
        AppCompatTextView mAppCompatTextViewContent;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String content) {
            mAppCompatTextViewContent.setText(content);
        }
    }
}
