package tena.health.care.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.student.app.R
import com.student.app.models.RecordedVideo
import com.student.app.screens.VideoPlayer.VideoPlayerActivity

class PopularProductAdapter(val context: Context,val activity: FragmentActivity, private val items: List<RecordedVideo>) :
    RecyclerView.Adapter<PopularProductAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_item_popular_products, parent, false)
        return ItemViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.ivProductImage)
        private val tvProductTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        private val tvProductDescription: TextView = itemView.findViewById(R.id.tvProductDescription)
        private val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val layoutAdd: LinearLayout = itemView.findViewById(R.id.layoutAdd)
        private val productHolder: CardView = itemView.findViewById(R.id.productHolder)

        fun bind(recordedVideo: RecordedVideo) {
            tvProductTitle.text = recordedVideo.videoName
            tvProductDescription.text = recordedVideo.subject
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.app_logo))
            tvProductPrice.text = recordedVideo.subject
            //imageView.setImageDrawable(product.imageUrl)
            layoutAdd.setOnClickListener {
                Log.e("Test","Layout Add Clicked")
            }
            productHolder.setOnClickListener {
                Log.e("Test","Layout Product Clicked")
                val intent = Intent(activity, VideoPlayerActivity::class.java)
                intent.putExtra("VideoUrl", recordedVideo.videoUrl)
                context.startActivity(intent)
            }
        }
    }
}