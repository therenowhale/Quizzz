import org.quizzz.auth.PasswordHasher
import org.mindrot.jbcrypt.BCrypt

class Argon2PasswordHasher : PasswordHasher {
    override fun verify(password: String, hash: String): Boolean {
        return checkpw(password, hash)
    }
}