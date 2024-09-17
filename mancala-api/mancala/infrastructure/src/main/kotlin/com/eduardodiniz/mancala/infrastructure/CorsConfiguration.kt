package com.eduardodiniz.mancala.infrastructure

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.io.IOException


@Profile("!prod")
@Component
class CorsConfiguration : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest?, res: ServletResponse, chain: FilterChain) {
        val request = req as HttpServletRequest?
        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT")
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, "
                + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
        chain.doFilter(req, res)
    }

    override fun init(filterConfig: FilterConfig?) {}

    override fun destroy() {}
}