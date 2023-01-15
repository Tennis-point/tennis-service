package com.tei.tenis.point.infrastracture.db.security

import io.jsonwebtoken.Jwts
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse
        val authHeader = httpServletRequest.getHeader("authorization")
        if ("OPTIONS" == httpServletRequest.method) {
            httpServletResponse.status = HttpServletResponse.SC_OK
            chain!!.doFilter(httpServletRequest, httpServletResponse)
            return
        } else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                sendUnathorized(httpServletResponse)
                return
            }
        }
        val token = authHeader!!.substring(7)
        val claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).body
        httpServletRequest.setAttribute("claims", claims)
        chain!!.doFilter(httpServletRequest, httpServletResponse)
    }

    private fun sendUnathorized(
        httpServletResponse: HttpServletResponse
    ) {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User needs to login first!");
        return
    }
}