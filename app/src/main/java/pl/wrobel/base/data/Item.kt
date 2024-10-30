package pl.wrobel.base.data

interface Item {
    val id: Int

    override fun equals(other: Any?): Boolean
}
