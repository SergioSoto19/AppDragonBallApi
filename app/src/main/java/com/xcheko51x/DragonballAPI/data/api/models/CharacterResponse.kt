package com.xcheko51x.DragonballAPI.data.api.models

import DBZCharacter


data class CharacterResponse(
    val items: List<DBZCharacter>,
    val meta: Meta
)