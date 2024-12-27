package info.sph.billing

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform