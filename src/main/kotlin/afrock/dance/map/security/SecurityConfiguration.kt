package afrock.dance.map.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.cors().and().csrf().disable().httpBasic().disable().build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        // TODO Evaluate CORS configuration
        val configuration = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowedMethods = listOf("GET", "POST")
            allowedHeaders = listOf("authorization", "content-type", "x-auth-token", "referrer-policy")
            exposedHeaders = listOf("x-auth-token")
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}