package sg.edu.np.mad.madpractical5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> userList;
    private ListActivity activity;

    public UserAdapter(ArrayList<User> userList, ListActivity activity) {
        this.userList = userList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (userList.get(viewType).getName().endsWith("7")) {
            // Inflate layout for names ending with 7
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list_image, parent, false);
        } else {
            // Inflate default layout
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        }
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = userList.get(position);
        holder.bind(currentUser);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
