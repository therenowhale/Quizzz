@Serializable
data class LoginResponse(
    val userId: Int,
    val username: String,
    val role: String
)
