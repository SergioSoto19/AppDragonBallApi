package com.xcheko51x.DragonballAPI.ui.screens.home.rv

import DBZCharacter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xcheko51x.DragonballAPI.R
import com.xcheko51x.DragonballAPI.ui.screens.CharacterDetail.CharacterDetailActivity

class CharacterAdapter(private var characters: List<DBZCharacter>, private val context: Context) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_personajes, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra("CHARACTER_ID", character.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = characters.size

    fun updateCharacters(newCharacters: List<DBZCharacter>) {
        characters = newCharacters
        notifyDataSetChanged()

    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivPersonaje: ImageView = view.findViewById(R.id.ivPersonaje)
        private val tvNomPersonaje: TextView = view.findViewById(R.id.tvNomPersonaje)

        fun bind(character: DBZCharacter) {
            tvNomPersonaje.text = character.name
            Glide.with(ivPersonaje.context)
                .load(character.image)
                .into(ivPersonaje)
        }
    }
}




