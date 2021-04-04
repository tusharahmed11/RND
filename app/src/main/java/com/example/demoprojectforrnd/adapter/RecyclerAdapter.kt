package com.example.demoprojectforrnd.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.demoprojectforrnd.R
import com.example.demoprojectforrnd.activity.DetailsActivity
import com.example.demoprojectforrnd.customviews.ribbon.RibbonLayout
import com.example.demoprojectforrnd.model.Model
import com.example.demoprojectforrnd.utils.ListDiffUtil

class RecyclerAdapter(private val context : Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){


    private var modelList = emptyList<Model>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView
        val ribbonLayout : RibbonLayout
       // val ribbonLayout2 : RibbonLayout

        init {
            // Define click listener for the ViewHolder's View.
            imageView = itemView.findViewById(R.id.firstImgLiveFeed)
            ribbonLayout = itemView.findViewById(R.id.ribbonLayout)
            //ribbonLayout2 = itemView.findViewById(R.id.FirstMainGridRowLinearCount)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.load(modelList[position].url){
            crossfade(600)
            error(R.drawable.ic_launcher_background)
        }


        holder.itemView.setOnClickListener {

            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
   /*     holder.ribbonLayout.ribbonHeader = RibbonFactory.getPresentRibbonHeader(context)
        holder.ribbonLayout2.ribbonHeader = RibbonFactory.getPresentRibbonHeader(context)*/

    }

    override fun getItemCount(): Int = modelList.size

    fun setData(newModelList: List<Model>){
        val modelsDiffUtil = ListDiffUtil(
            modelList,
            newModelList
        )
        val duffUtilResult = DiffUtil.calculateDiff(modelsDiffUtil)
        modelList = newModelList
        duffUtilResult.dispatchUpdatesTo(this)
    }
}