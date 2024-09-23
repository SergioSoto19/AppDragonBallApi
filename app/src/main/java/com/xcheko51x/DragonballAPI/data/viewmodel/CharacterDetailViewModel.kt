package com.xcheko51x.DragonballAPI.data.viewmodel

import DBZCharacter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xcheko51x.DragonballAPI.data.api.models.Transformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel : ViewModel() {
    private val _character = MutableLiveData<DBZCharacter>()
    val character: LiveData<DBZCharacter> = _character
    private val _transformations = MutableLiveData<List<Transformation>>()

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadCharacterDetails(characterId: Int) {
        _isLoading.value = true

        NetworkManager.api.getCharacterById(characterId).enqueue(object : Callback<DBZCharacter> {
            override fun onResponse(call: Call<DBZCharacter>, response: Response<DBZCharacter>) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _character.value = response.body()
                    _transformations.value = response.body()!!.transformations
                } else {
                    Log.e("CharacterDetailViewModel", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<DBZCharacter>, t: Throwable) {
                _isLoading.value = false
                Log.e("CharacterDetailViewModel", "Error: ${t.message}")
            }
        })
    }
}
