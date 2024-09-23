package com.xcheko51x.DragonballAPI.data.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.ui.screens.characters.uiState.CharactersUiState
import com.xcheko51x.DragonballAPI.data.api.models.CharacterResponse


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _charactersUiState = MutableLiveData<CharactersUiState>()
    val charactersUiState: LiveData<CharactersUiState> get() = _charactersUiState
    private var page = 1
    init {getListOfCharacters()}

    fun getListOfCharacters() {
        _charactersUiState.value = CharactersUiState(isLoading = true)
        NetworkManager.api.getListOfCharacters(page, 50).enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { characterResponse ->
                        _charactersUiState.postValue(
                            CharactersUiState(
                                isLoading = false,
                                characters = characterResponse.items
                            )
                        )
                    }
                } else {
                    _charactersUiState.postValue(
                        CharactersUiState(
                            isLoading = false,
                            errorMessage = "Error: ${response.code()}"
                        )
                    )
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                _charactersUiState.postValue(
                    CharactersUiState(
                        isLoading = false,
                        errorMessage = "Error de red: ${t.localizedMessage}"
                    )
                )
            }
        })
    }
}




