package com.xcheko51x.DragonballAPI.ui.screens.home.rv
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.recyclerview.widget.GridLayoutManager
import com.xcheko51x.DragonballAPI.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.rickandmortyapp.ui.screens.characters.uiState.CharactersUiState
import com.xcheko51x.DragonballAPI.data.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CharacterAdapter(emptyList(), this)
        binding.rvPersonajes.layoutManager = GridLayoutManager(this, 3)
        binding.rvPersonajes.adapter = adapter

        // Observar los cambios en el estado de la UI
        viewModel.charactersUiState.observe(this) { uiState ->
            updateUI(uiState)
        }

        binding.tietBuscar.addTextChangedListener { text ->
            val query = text.toString().trim()
            filterCharacters(query)
        }

        binding.tietBuscar.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && binding.tietBuscar.text.toString() == "Buscar") {
                binding.tietBuscar.setText("")
            }
        }

        viewModel.getListOfCharacters()
    }

    private fun updateUI(uiState: CharactersUiState) {
        if (uiState.isLoading) {
            binding.pbCharacters.visibility = View.VISIBLE
            binding.llContent.visibility = View.GONE
        } else {

            binding.pbCharacters.visibility = View.GONE
            binding.llContent.visibility = View.VISIBLE
            adapter.updateCharacters(uiState.characters)
        }
    }

    private fun filterCharacters(query: String) {
        val filteredCharacters = viewModel.charactersUiState.value?.characters?.filter { character ->
            character.name.contains(query, ignoreCase = true)
        } ?: emptyList()
        adapter.updateCharacters(filteredCharacters)
    }
}