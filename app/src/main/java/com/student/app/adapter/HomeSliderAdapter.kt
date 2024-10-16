package tena.health.care.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.student.app.R
import tena.health.care.models.HomeSlider

class HomeSliderAdapter(val context: Context, private val images: List<HomeSlider>) : RecyclerView.Adapter<HomeSliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
        return SliderViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class SliderViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(homeSlider: HomeSlider) {
            imageView.setImageDrawable(homeSlider.imageUrl)
        }
    }
}