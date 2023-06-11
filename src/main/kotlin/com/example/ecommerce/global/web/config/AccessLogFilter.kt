package com.example.ecommerce.global.web.config

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AccessLogFilter : Filter {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val startTime: Long = System.currentTimeMillis()

        val httpRequest: HttpServletRequest = request as HttpServletRequest
        val httpResponse: HttpServletResponse = response as HttpServletResponse

        log.info(
            "HttpMethod : [{}], RequestURI : [{}], QueryString : [{}], UserAgent : [{}], RequestIp : [{}]",
            httpRequest.method,
            httpRequest.requestURI,
            httpRequest.queryString ?: "",
            httpRequest.getHeader("User-Agent"),
            httpRequest.remoteAddr
        )

        try {
            chain.doFilter(httpRequest, httpResponse)
        } finally {
            val elapsedTime: Long = System.currentTimeMillis() - startTime

            log.info("응답 Status : [{}], 요청 처리 시간 : [{}ms]", HttpStatus.valueOf(httpResponse.status), elapsedTime)
        }
    }
}
