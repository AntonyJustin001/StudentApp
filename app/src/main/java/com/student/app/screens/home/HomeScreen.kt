package com.student.app.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.firestore.FirebaseFirestore
import com.student.app.R
import com.student.app.models.RecordedVideo
import tena.health.care.adapter.HomeAdapter
import tena.health.care.models.HomeSlider

class HomeScreen : Fragment() {

    private lateinit var homeScrollView: ScrollView
    private lateinit var rcHome: RecyclerView
    private lateinit var progressBar: LottieAnimationView
    private lateinit var noNetworkContainer: RelativeLayout

    var videosList = mutableSetOf<RecordedVideo>()
    var homeSliderList = mutableSetOf<HomeSlider>()
    var homeItemsList : MutableList<Pair<HomeSlider?, RecordedVideo?>> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        rcHome = view.findViewById(R.id.rcHome)
        homeScrollView = view.findViewById(R.id.homeScrollView)

        // Parse SliderList and ProductList from HomeItemsList
        homeSliderList.add(HomeSlider(imageUrl = ContextCompat.getDrawable(requireContext(), R.drawable.sample_banner1)))
        homeSliderList.add(HomeSlider(imageUrl = ContextCompat.getDrawable(requireContext(), R.drawable.sample_banner2)))
        homeSliderList.add(HomeSlider(imageUrl = ContextCompat.getDrawable(requireContext(), R.drawable.sample_banner1)))

        getAllRecordedVideos { videos ->

            val confirmedVideos = videos.toMutableList()
            videos.forEach {
                Log.e("videos","video - $it")
            }

            videos.forEach {
                if(it.status == "Approved") {
                    videosList.add(it)
                    Log.e("videos","video - $it")
                }
            }

            val maxSize = maxOf(homeSliderList.size, videosList.size)
            for (i in 0 until maxSize) {
                val slider = if (i < homeSliderList.size) homeSliderList.toList()[i] else null
                val product = if (i < videosList.size) videosList.toList()[i] else null
                homeItemsList.add(Pair(slider, product))
            }

            rcHome.layoutManager = LinearLayoutManager(context)
            rcHome.adapter = HomeAdapter(requireContext(),requireActivity(),homeItemsList)
        }

    }

    fun getAllRecordedVideos(onProductsRetrieved: (List<RecordedVideo>) -> Unit) {
        progressBar.visibility = View.VISIBLE
        val db = FirebaseFirestore.getInstance()
        db.collection("videos")
            .get()
            .addOnSuccessListener { result ->
                val videoList = mutableListOf<RecordedVideo>()
                for (document in result) {
                    val video = document.toObject(RecordedVideo::class.java)
                    videoList.add(video)
                }
                onProductsRetrieved(videoList)
                progressBar.visibility = View.GONE
            }
            .addOnFailureListener { e ->
                // Handle the error
                println("Error getting products: $e")
                progressBar.visibility = View.GONE
            }
    }

}