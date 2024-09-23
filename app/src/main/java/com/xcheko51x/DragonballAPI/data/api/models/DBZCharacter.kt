import com.xcheko51x.DragonballAPI.data.api.models.Gender
import com.xcheko51x.DragonballAPI.data.api.models.Planet
import com.xcheko51x.DragonballAPI.data.api.models.Transformation

data class DBZCharacter(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: Gender,
    val description: String,
    val image: String,
    val affiliation: String,
    val originPlanet: Planet? = null,
    val transformations: List<Transformation> = emptyList()
)






