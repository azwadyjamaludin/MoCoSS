package upsi.edu.mocos.ui_component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import upsi.edu.mocos.R;

public class CustomButtonNoImage extends LinearLayout {
    View rootView;
    TextView customTextNoImage;

    public CustomButtonNoImage(Context context) {
        super(context);
    }

    public CustomButtonNoImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CustomButtonNoImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }


    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.custom_button_no_image_layout,this);
        customTextNoImage = rootView.findViewById(R.id.customText);
        onClickHandler();

    }

    private void onClickHandler() {
        customTextNoImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
