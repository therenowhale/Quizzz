 package org.quizzz

  import io.ktor.http.HttpStatusCode
  import io.ktor.server.request.receive
  import io.ktor.server.response.respond
  import io.ktor.server.routing.Route
  import io.ktor.server.routing.post

  fun Route.authRoutes(authService: AuthService) {
      post("/auth/login") {
          val request = call.receive<LoginRequest>()
          val user = authService.login(request)

          if (user == null) {
              call.respond(
                  HttpStatusCode.Unauthorized,
                  ErrorResponse("Invalid username or password")
              )
              return@post
          }

          call.respond(
              LoginResponse(
                  userId = user.id,
                  username = user.username,
                  role = user.role
              )
          )
      }
  }