import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vishxwas.bharatindia.News
import com.vishxwas.bharatindia.R

class newsListAdapter(private val listener:newsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val item: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newsitem, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onClick(item[viewHolder.adapterPosition])
        }

        return viewHolder;
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size;
    }
    fun updateItems(updatedItem:ArrayList<News>){
        item.clear()

        item.addAll(updatedItem)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val titleView:TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
    val date: TextView = itemView.findViewById(R.id.published)
}
interface newsItemClicked {
    fun onClick(item: News)
}