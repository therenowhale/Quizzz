@Serializable
data class LoginRequest(
    val usernameOrEmail: String,
    val password: String
    )