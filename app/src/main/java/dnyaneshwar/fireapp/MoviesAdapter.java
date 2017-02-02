package dnyaneshwar.fireapp;

/**
 * Created by Dnyaneshwar on 12/24/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,  genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tittle);
            genre = (TextView) view.findViewById(R.id.genre);
          //  year = (TextView) view.findViewById(R.id.year);
        }
    }
//dnyaneshar Qlkjtishvb dnyaneshwar Mdlajwj the

    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}