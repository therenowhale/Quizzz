class AuthService(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    fun login(request: LoginRequest): UserRecord? {
        val user = userRepository.findUserOrEmail(request.usernameOrEmail) ?: return null
        
        return if(
            passwordHasher.verify(
                request.password, 
                    user.password
                )
            ){
                user
        } else {
            null
        }
    }
}