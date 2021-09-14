package in.pm.nearapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.pm.nearapp.R;

public class DashBoard extends AppCompatActivity {

    BottomSheetBehavior sheetBehavior;

    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    CardView English, Hindi, Odia, Maharastrian, Gujrati, Bangla;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        init();


        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);


        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }
    private void init(){
        English= findViewById(R.id.english);
        Hindi= findViewById(R.id.hindi);
        Odia= findViewById(R.id.odia);
        Maharastrian= findViewById(R.id.maharastrian);
        Gujrati= findViewById(R.id.gujrati);
        Bangla= findViewById(R.id.bangla);
    }
}