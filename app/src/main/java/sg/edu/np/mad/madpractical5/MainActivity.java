package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonFollow;
    private TextView tvName, tvDescription;
    private User currentUser;
    private DBHandler dbHandler;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);

        // Initialize views
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        buttonFollow = findViewById(R.id.btnFollow);
        buttonBack = findViewById(R.id.btnBack);

        // Get user information from intent
        Intent intent = getIntent();
        if (intent != null) {
            currentUser = (User) intent.getSerializableExtra("user");
            if (currentUser != null) {
                // Set user information
                tvName.setText(currentUser.getName());
                tvDescription.setText(currentUser.getDescription());
                updateButtonMessageText();
            }
        }

        // Setup follow button click listener
        setupFollowButton();

        // Setup back button click listener
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Handle back press using OnBackPressedCallback
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setResult(RESULT_OK); // Set the result to OK
                finish(); // Close the activity
            }
        });
    }

    private void setupFollowButton() {
        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle follow state
                currentUser.setFollowed(!currentUser.getFollowed());
                updateButtonMessageText();
                dbHandler.updateUser(currentUser);
                String toastMessage = currentUser.getFollowed() ? "Following" : "Not Following";
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateButtonMessageText() {
        buttonFollow.setText(currentUser.getFollowed() ? "Unfollow" : "Follow");
    }
}
