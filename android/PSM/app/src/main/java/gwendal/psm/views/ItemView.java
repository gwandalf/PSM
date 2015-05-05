package gwendal.psm.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import gwendal.psm.R;
import model.ModelItem;

/**
 * Created by gwendal on 04/05/15.
 */
public class ItemView extends LinearLayout implements Observer {

    /**
     * Text view.
     */
    public TextView textView;

    /**
     * Image button representing a cross..
     */
    public ImageButton destroy;

    /**
     * Constructor.
     * @param context Context.
     */
    public ItemView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        init(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs GUI Attributes.
     */
    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyleAttr Styled attributes.
     */
    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Common body of all the constructors.
     * @param context Context.
     */
    private void init(Context context) {
        this.textView = new TextView(context, null, R.attr.style);
        this.destroy = new ImageButton(context);
        this.destroy.setImageResource(R.drawable.cross24);
        LayoutParams myParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(this.textView, myParams);
        addView(this.destroy, myParams);
    }

    @Override
    public void update(Observable observable, Object data) {
        String arg = (String)data;
        ModelItem model = (ModelItem)observable;
        if("name".equals(arg)) {
            this.textView.setText(model.getName());
        }
    }

    /**
     * Gets the advised parent layout params.
     * @return The advised parent layout params.
     *
    public LayoutParams getParams() {
        return this.params;
    }*/

    /**
     * Sets the advised parent layout params.
     *
    private void setParams() {
        this.params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.params.setMargins(16, 16, 16, 16);
    }*/
}
