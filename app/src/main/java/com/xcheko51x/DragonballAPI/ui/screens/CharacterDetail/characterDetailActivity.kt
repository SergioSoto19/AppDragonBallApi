package com.xcheko51x.DragonballAPI.ui.screens.CharacterDetail


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xcheko51x.DragonballAPI.R
import com.xcheko51x.DragonballAPI.data.viewmodel.CharacterDetailViewModel
import com.xcheko51x.DragonballAPI.ui.screens.CharacterDetail.rv.TransformationsAdapter

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var tvCharacterName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivCharacter: ImageView
    private lateinit var recyclerViewTransformations: RecyclerView
    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private lateinit var transformationsAdapter: TransformationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        tvCharacterName = findViewById(R.id.tvCharacterName)
        tvDescription = findViewById(R.id.tvDescription)
        ivCharacter = findViewById(R.id.ivCharacter)
        recyclerViewTransformations = findViewById(R.id.recyclerViewTransformations)
        characterDetailViewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        val characterId = intent.getIntExtra("CHARACTER_ID", -1)
        if (characterId != -1) {
            characterDetailViewModel.loadCharacterDetails(characterId)
        }

        recyclerViewTransformations.layoutManager = LinearLayoutManager(this)
        transformationsAdapter = TransformationsAdapter(emptyList())
        recyclerViewTransformations.adapter = transformationsAdapter

        characterDetailViewModel.character.observe(this, { character ->
            character?.let {
                tvCharacterName.text = character.name
                tvDescription.text = character.description

                Glide.with(this).load(character.image).into(ivCharacter)

                transformationsAdapter.setTransformations(character.transformations)
            }
        })
    }
}