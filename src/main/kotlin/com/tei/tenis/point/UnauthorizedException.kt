package com.tei.tenis.point

import java.lang.RuntimeException

class UnauthorizedException : RuntimeException {
    constructor(message: String?) : super(message)
}
