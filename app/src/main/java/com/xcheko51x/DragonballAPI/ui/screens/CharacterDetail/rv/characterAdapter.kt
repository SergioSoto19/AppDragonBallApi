package com.xcheko51x.DragonballAPI.ui.screens.CharacterDetail.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xcheko51x.DragonballAPI.R
import com.xcheko51x.DragonballAPI.data.api.models.Transformation

class TransformationsAdapter(private var transformations: List<Transformation>) :
    RecyclerView.Adapter<TransformationsAdapter.TransformationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transformation, parent, false)
        return TransformationViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransformationViewHolder, position: Int) {
        val transformation = transformations[position]
        holder.bind(transformation)
    }
    override fun getItemCount(): Int = transformations.size

    fun setTransformations(newTransformations: List<Transformation>) {
        transformations = newTransformations
        notifyDataSetChanged()
    }
    class TransformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val transformationNameTextView: TextView = itemView.findViewById(R.id.tvTransformationName)
        private val transformationImageView: ImageView = itemView.findViewById(R.id.ivTransformationImage)

        fun bind(transformation: Transformation) {
            transformationNameTextView.text = transformation.name
            Glide.with(itemView.context).load(transformation.image).into(transformationImageView)
        }
    }
}




