package com.example.checklistimaguru;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;


public class elementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_grid_element);
        final CheckList checkList;
        Bundle arguments = getIntent().getExtras();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        final ConstraintLayout constraintLayout = findViewById(R.id.constr);
        TableLayout tableLayout;
        TextView roomName;
        TextView eventName;
        TextView beginTime;
        TextView endTime;
        TextView chairs;
        TextView tables;
        TextView seating;
        TextView seatingScene;
        TextView projector;
        TextView computer;
        TextView presenter;
        TextView microphone;
        TextView speakers;
        TextView coffeePause;
        TextView food;
        TextView timeAndPlaceCoffee;
        TextView flipChart;
        TextView videoRecording;
        TextView onlineRecording;
        TextView registrToEvent;
        TextView registrHelp;
        TextView announcement;
        TextView registrOnline;
        TextView noticeOfChange;
        TextView comments;
        roomName = findViewById(R.id.roomName);
        eventName = findViewById(R.id.eventName);
        beginTime = findViewById(R.id.beginTime);
        endTime = findViewById(R.id.endTime);
        chairs = findViewById(R.id.chairs);
        tables = findViewById(R.id.tables);
        seating = findViewById(R.id.seatingPlace);
        seatingScene = findViewById(R.id.seatingScene);
        projector = findViewById(R.id.projector);
        computer = findViewById(R.id.computer);
        presenter = findViewById(R.id.presenter);
        microphone = findViewById(R.id.microphone);
        speakers = findViewById(R.id.speakers);
        coffeePause = findViewById(R.id.coffeeBreak);
        food = findViewById(R.id.food);
        timeAndPlaceCoffee = findViewById(R.id.timeAndPlaceCoffee);
        flipChart = findViewById(R.id.flipChart);
        videoRecording = findViewById(R.id.videoRecording);
        onlineRecording = findViewById(R.id.onlineRecording);
        registrToEvent = findViewById(R.id.registrationToEvent);
        registrHelp = findViewById(R.id.registrationHelp);
        announcement = findViewById(R.id.announcement);
        registrOnline = findViewById(R.id.registratonOnline);
        noticeOfChange = findViewById(R.id.noticeOfChange);
        comments = findViewById(R.id.comments);
        tableLayout = findViewById(R.id.tableLayout);

        try {
            checkList = (CheckList) arguments.getSerializable(CheckList.class.getSimpleName());
            setTitle(checkList.getNAME());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor(checkList.getColor()));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(checkList.getColor())));
            }
            try {
                eventName.setText(checkList.getNAME());
            }catch (NullPointerException e){
                eventName.setText("nameerror");
            }
            try {
                beginTime.setText(String.format("%s %s", checkList.getBeginTime(), checkList.getDate()));
            }catch (NullPointerException e){
                beginTime.setText("timeerror");
            }
            try {
                endTime.setText(String.format("%s %s", checkList.getEndTime(), checkList.getDate()));
            }catch (NullPointerException e){
                endTime.setText("Tim2error");
            }
            try {
                roomName.setText(checkList.getRoom());
            }catch (NullPointerException e){
                roomName.setText("roomError");
            }
            try {
                chairs.setText(checkList.getChairs());
            }catch (NullPointerException e){
                chairs.setText("ChairError");
            }
            try {
                tables.setText(checkList.getTables());
            } catch (NullPointerException e){
                tables.setText("tablesError");
            }
            try {
                seating.setText(checkList.getSeating());
            }catch (NullPointerException e){seating.setText("seaterror");}
            seatingScene.setText(checkList.getScene());
            projector.setText(checkList.getProjector());
            computer.setText(checkList.getComputer());
            presenter.setText(checkList.getPresenter());
            microphone.setText(checkList.getMicrophone());
            speakers.setText(checkList.getSpeakers());
            coffeePause.setText(checkList.getCoffeePause());
            food.setText(checkList.getFood());
            timeAndPlaceCoffee.setText(checkList.getTimeAndPlaceCoffee());
            flipChart.setText(checkList.getFlipChart());
            videoRecording.setText(checkList.getVideoRecording());
            onlineRecording.setText(checkList.getVideoTranslation());
            registrToEvent.setText(checkList.getRegistrationToEvent());
            registrHelp.setText(checkList.getHelpRegistration());
            announcement.setText(checkList.getAnnouncement());
            registrOnline.setText(checkList.getRegistrationOnline());
            noticeOfChange.setText(checkList.getNoticeOfChange());
            comments.setText(checkList.getComments());
        }catch (NullPointerException e){
            speakers.setText("Error");
        }
        goneNotInstalled(constraintLayout, true);
        tableLayout.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            Boolean b = false;
            @Override
            public void onSingleClick(View view) {

            }

            @Override
            public void onDoubleClick(View view) {
                goneNotInstalled(constraintLayout, b);
                b = !b;
            }
        }));
    }
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }
    public void goneNotInstalled(View v, Boolean b) {
        View g = (View) v.getParent();
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    // recursively call this method
                    goneNotInstalled(child, b);
                }
            } else if (v instanceof TextView) {
                Animation slideBT = AnimationUtils.loadAnimation(this, R.anim.grid_hide);
                Animation slideTB = AnimationUtils.loadAnimation(this, R.anim.grid_show);
                if (((TextView) v).getText().equals("Не установлено"))
                    if(b) {
                        g.setAnimation(slideBT);
                        g.setVisibility(View.GONE);


                    }
                    else {
                        g.setAnimation(slideTB);
                        g.setVisibility(View.VISIBLE);

                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
