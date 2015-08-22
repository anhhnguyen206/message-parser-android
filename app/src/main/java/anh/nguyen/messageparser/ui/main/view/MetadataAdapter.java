package anh.nguyen.messageparser.ui.main.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class MetadataAdapter extends RecyclerView.Adapter<MetadataAdapter.BaseViewHolder> {
    private final static int HEADER_VIEW_TYPE = 0;
    private final static int ITEM_VIEW_TYPE = 1;
    private List<MessageMetadataItem> mMessageMetadataItems;

    public MetadataAdapter(List<MessageMetadataItem> messageMetadataItems) {
        mMessageMetadataItems = messageMetadataItems;
    }

    @Override
    public MetadataAdapter.BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        switch (viewType) {
            case HEADER_VIEW_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header, viewGroup, false);
                return new HeaderViewHolder(view);
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_metadata, viewGroup, false);
                return new ItemViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        MessageMetadataItem item = mMessageMetadataItems.get(position);

        if (item.getType() == MessageMetadataItem.HEADER) {
            return HEADER_VIEW_TYPE;
        } else {
            return ITEM_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(MetadataAdapter.BaseViewHolder viewHolder, int position) {
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

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        abstract void bind(String content);
    }

    class HeaderViewHolder extends BaseViewHolder {
        @Bind(R.id.content)
        TextView mTextViewContent;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String content) {
            mTextViewContent.setText(content);
        }
    }

    class ItemViewHolder extends BaseViewHolder {
        @Bind(R.id.content)
        TextView mTextViewContent;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String content) {
            mTextViewContent.setText(content);
        }
    }
}
