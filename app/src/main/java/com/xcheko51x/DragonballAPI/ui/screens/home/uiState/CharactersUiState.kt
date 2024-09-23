package com.example.rickandmortyapp.ui.screens.characters.uiState

import DBZCharacter

data class CharactersUiState(
    val isLoading: Boolean = false,
    val characters: List<DBZCharacter> = emptyList(),
    val errorMessage: String? = null
)
